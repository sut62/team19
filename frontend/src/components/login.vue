<template>
  <v-container
        fluid
        fill-height
        class="application"
      >
        <v-layout
          align-center
          justify-center
        >
          <v-flex
            xs12
            sm8
            md4
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>เข้าสู่ระบบในฐานะพนักงาน</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    solo
                    label="บัญชีผู้ใช้"
                    v-model="username"
                    required
              ></v-text-field>
                  <v-text-field
                  v-model="password"
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show1 ? 'text' : 'password'"
                  name="input-10-1"
                  label="รหัสผ่าน"
                  hint="At least 8 characters"
                  solo
                  @click:append="show1 = !show1"
          ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="Check">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>

</template>

<script>
import http from "../http-common";

export default {
  name: "operator",
  data() {
    return {
      show1: false,
      rules: {
          required: value => !!value || 'Required.',
          min: v => v.length >= 8 || 'Min 8 characters',
        },
        username: "",
        password :"",
        valid: false,

    };
  },
  

  methods: {
    Check() {
      http
        .get("/employee/" +
            this.username +"/"+this.password
          )
        .then(response => {
          if (response.data[0] != null) {
            localStorage.setItem("Name",response.data[0].name);
            localStorage.setItem("Id",response.data[0].id);
            this.$router.push("/menu");

          }
          else{
          alert("Username หรือ Password ผิด")
          }
          })
    },
  
  },
};
</script>
