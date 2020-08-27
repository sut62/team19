<template>
  <v-container class="fill-height">
    <v-row align="center" justify="center" no-gutters>
      <v-col cols="12" sm="8" md="8" class>
        <v-card class="evelation-12 card" style="background: #616161;">
          <v-window>
            <!--SignUp-->
            <v-window-item :value="2">
              <v-row class="fill-height">
                <v-col cols="12" md="4" class="darken-2 vcenter" :class="`${bgColor}`">
                  <div>
                    <v-card-text :class="`${fgColor}--text`">
                      <h1 class="text-center headline mb-3">Already a user?</h1>
                      <h5 class="text-center overline mb-3">Please Sign In</h5>
                    </v-card-text>
                    <div class="text-center mb-6">
                      <v-btn dark outlined href="/">Sign In</v-btn>
                    </div>
                  </div>
                </v-col>
                <v-col cols="12" md="8" class="pt-6 pb-6">
                  <v-card-text>
                    <h1 class="text-center display-1 mb-10" :class="`${bgColor}--text`">Sign Up</h1>
                    <v-form class="signup-form-form" @submit.prevent="signup">
                      <v-text-field
                        id="name"
                        v-model="name"
                        label="Name"
                        type="text"
                        solo
                      />
                      <v-text-field
                        id="username"
                        v-model="username"
                        label="Username"
                        append-icon="person"
                        type="text"
                        solo
                      />
                      <v-text-field
                        id="email"
                        v-model="email"
                        label="E-Mail"
                        append-icon="email"
                        type="email"
                        solo
                      />
                      <v-text-field
                        id="password"
                        v-model="password"
                        label="Password"
                        append-icon="lock"
                        type="password"
                        solo
                      />
                      <div class="text-center mt-6">
                        <v-btn type="submit" large :color="bgColor" dark @click="Check">Sign Up</v-btn>
                      </div>
                    </v-form>
                  </v-card-text>
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
//import { version, description } from "vue-simple-alert/package.json";

export default {
  name: "member",
  data() {
    return {
      name: "",
      username: "",
      password: "",
      email: "",
      valid: false,
      checkSave: false,
      alwayselect: false,
    };
  },
  props: {
    source: {
      type: String,
      default: "",
    },
    bgColor: {
      type: String,
      default: "teal",
    },
    fgColor: {
      type: String,
      default: "white",
    },
  },

  methods: {
    Check() {
      http
        .post(
          "/login/" +
            this.name +
            "/" +
            this.username +
            "/" +
            this.password +
            "/" +
            this.email +
            "/" ,
          this.login,
        )
    }
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