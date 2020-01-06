<template>
  <v-app style = "background: #ADD8E6;">
    <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>ระบบเช่ารถ</span>
        <v-btn style="margin-left: 10px;" v-if="menu()" color="primary" href="/menu" target="_self">menu</v-btn>
        
      </v-toolbar-title>
      <v-spacer></v-spacer>
      
        <span class="mr-2" v-if="login()">{{name}}</span>
        <v-btn v-if="logoutb()" color="error" @click="logout">LOGOUT</v-btn>
    </v-app-bar>

    <v-content>
      <!-- <VideoRental/> -->
      <router-view />
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      name : "",
      id   : "",
      valid: false,
    };
  }, 
  methods: {
    login() {
      this.name = localStorage.getItem("Name");
      return this.$router.history.current["path"] !== "/login" && this.$router.history.current["path"] !== "/loginm" && this.$router.history.current["path"] !== "/";
    },
    logoutb(){
      return this.$router.history.current["path"] !== "/login" && this.$router.history.current["path"] !== "/loginm" && this.$router.history.current["path"] !== "/";
    },
    menu(){
      return this.$router.history.current["path"] !== "/login" && this.$router.history.current["path"] !== "/loginm" && this.$router.history.current["path"] !== "/" && this.$router.history.current["path"] !== "/menu" && this.$router.history.current["path"] !== "/maintenance" && this.$router.history.current["path"] !== "/viewmt";
    },
    logout() {
      localStorage.removeItem("Name");
      localStorage.removeItem("Id");
      this.$router.push("/");
    }
  }
  
};

</script>
