<template>
  <v-container class="application">
    <v-row align="center" justify="center" >
      <v-col cols="20" sm="8" md="8" class>
        <v-card class="evelation-12 card" style="background: #616161;">
          <v-window>
            <!--SignIn-->
            <v-window-item>
              <v-row justify="center">
                <v-col cols="12" md="8" class="pt-6 pb-6">
                  <v-card-text>
                    <v-form class="signup-form-form" @submit.prevent="signin">
                      <h1 class="text-center display-1 mb-10" :class="`${bgColor}--text`">Sign in</h1>
                      <v-text-field
                        v-model="username"
                        label="Username"
                        append-icon="person"
                        :rules = "[(v) => !!v || 'กรุณาใส่ username']"
                        type="text"
                        solo
                      />
                      <v-text-field
                        v-model="password"
                        label="Password"
                        append-icon="lock"
                        :rules = "[(v) => !!v || 'กรุณาใส่ password']"
                        type="password"
                        solo
                      />
                      <div class="text-center mt-6">
                        <v-btn type="submit" large :color="bgColor" dark @click="Check">Sign In</v-btn>
                      </div>
                    </v-form>
                  </v-card-text>
                </v-col>
                <v-col cols="12" md="4" class="darken-2 vcenter" :class="`${bgColor}`">
                  
                  <div>
                    <v-card-text :class="`${fgColor}--text`">
                      <h1 class="text-center headline mb-3">No User?</h1>
                      <h5 class="text-center overline mb-3">Please Sign Up to continue</h5>
                    </v-card-text>
                    <div class="text-center mb-6">
                      <v-btn dark outlined href="/signup" target="_self" >SIGN UP</v-btn>
                    </div>
                  </div>
                </v-col>
              </v-row>
            </v-window-item>
          </v-window>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "login",
  data() {
    return {
      username: null,
      password: null,
      valid: false,
    };
  },
  props: {
    source: {
      type: String,
      default: ''
    },
    bgColor: {
      type: String,
      default: 'teal'
    },
    fgColor: {
      type: String,
      default: 'white'
    }
  },

  methods: {
    Check() {
      http
        .get("/login/" + this.username + "/" + this.password)
        .then((response) => {
          if (response.data[0] != null) {
            localStorage.setItem("Name", response.data[0].name);
            localStorage.setItem("Id", response.data[0].id);
            this.$alert(
              "Login Success",
              "Welcome",
              "success"
            )
            this.$router.push("/menu");
          }else {
            this.$alert(
              "Username or Password Incorrect",
              "Error",
              "warning"
            )
          }
        });
    },
  },
};
</script>

<style scoped lang="scss">
.v-input__icon--double .v-input__icon {
  margin-left: -4.25rem !important;
}
a.no-text-decoration {
  text-decoration: none;
}
#signinup-form {
  max-width: 75rem;
}
.signup-form-form {
  max-width: 23rem;
  margin: 0 auto;
}
.card {
  overflow: hidden;
}
.vcenter {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>