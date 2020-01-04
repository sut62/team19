<template>
  <v-app >
  <div class="blue lighten-4">
    <v-content>
      <router-link to="/">Home</router-link>
      <router-view />
    </v-content>
    </div>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
    username: '',
  }),
};
</script>
