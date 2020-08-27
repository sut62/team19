<template>
  <v-container>
    <div class="text-center">
      <h2>เหล่าฮีโร่อีกมากมาย!</h2>
    </div>
    <v-card width="1200" class="col-md">
      <v-tabs v-model="tab" background-color="white" color="deep-purple accent-4" right>
        <v-tab v-for="tabname in tabnames" :key="tabname.text">
          <v-tab-item-avatar style="margin-right: 5px;">
            <v-img :src="tabname.avatar" max-width="15"></v-img>
          </v-tab-item-avatar>
          <v-tab-item-content>
            <v-tab-item-title>{{tabname.text}}</v-tab-item-title>
          </v-tab-item-content>
        </v-tab>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item v-for="tabname in tabnames" :key="tabname">
          <v-flex shrink display-1></v-flex>
          <!------------------------------------------------------------------------>
          <!-------------------- หน้า All Heroes --------------------------------------->
          <!------------------------------------------------------------------------>
          <v-flex transparent-body v-if="tabname.text === 'All Heroes'">
            <v-row justify="center">
              <div style="margin-left: 25px;" v-for="(character, index) in characters" :key="index">
                <router-link
                  :to="{  name: 'character-details',
                        params: { character: character, id: character.id }
                      }"
                >
                  <v-btn tile class="text-center ma-2" title width="150">
                    <v-card-btn>
                      <v-img :src="character.img_small" max-width="36" style="margin-right: 15px;"></v-img>
                    </v-card-btn>
                    <v-card-btn>{{character.name}}</v-card-btn>
                  </v-btn>
                </router-link>
              </div>
            </v-row>
          </v-flex>
          <!------------------------------------------------------------------------>
          <!-------------------- หน้า Assasin --------------------------------------->
          <!------------------------------------------------------------------------>
          <v-flex transparent-body v-if="tabname.text === 'Assassin'">
            <v-row justify="center">
              <div style="margin-left: 25px;" v-for="(character, index) in characters" :key="index">
                <v-btn
                  tile
                  class="text-center ma-2"
                  title
                  width="150"
                  v-if="character.type1_heroes === 'Assassin' || character.type2_heroes === 'Assassin'"
                >
                  <v-card-btn>
                    <v-img :src="character.img_small" max-width="36" style="margin-right: 25px;"></v-img>
                  </v-card-btn>
                  <v-card-btn>{{character.name}}</v-card-btn>
                </v-btn>
              </div>
            </v-row>
          </v-flex>
          <!------------------------------------------------------------------------>
          <!-------------------- หน้า Fighter --------------------------------------->
          <!------------------------------------------------------------------------>
          <v-flex transparent-body v-if="tabname.text === 'Fighter'">
            <v-row justify="center">
              <div style="margin-left: 25px;" v-for="(character, index) in characters" :key="index">
                <v-btn
                  tile
                  class="text-center ma-2"
                  title
                  width="150"
                  v-if="character.type1_heroes === 'Fighter' || character.type2_heroes === 'Fighter'"
                >
                  <v-card-btn>
                    <v-img :src="character.img_small" max-width="36" style="margin-right: 25px;"></v-img>
                  </v-card-btn>
                  <v-card-btn>{{character.name}}</v-card-btn>
                </v-btn>
              </div>
            </v-row>
          </v-flex>
          <!------------------------------------------------------------------------>
          <!----------------------- หน้า Mage --------------------------------------->
          <!------------------------------------------------------------------------>
          <v-flex transparent-body v-if="tabname.text === 'Mage'">
            <v-row justify="center">
              <div style="margin-left: 25px;" v-for="(character, index) in characters" :key="index">
                <v-btn
                  tile
                  class="text-center ma-2"
                  title
                  width="150"
                  v-if="character.type1_heroes === 'Mage' || character.type2_heroes === 'Mage'"
                >
                  <v-card-btn>
                    <v-img :src="character.img_small" max-width="36" style="margin-right: 25px;"></v-img>
                  </v-card-btn>
                  <v-card-btn>{{character.name}}</v-card-btn>
                </v-btn>
              </div>
            </v-row>
          </v-flex>
          <!------------------------------------------------------------------------>
          <!----------------------- หน้า Carry -------------------------------------->
          <!------------------------------------------------------------------------>
          <v-flex transparent-body v-if="tabname.text === 'Carry'">
            <v-row justify="center">
              <div style="margin-left: 25px;" v-for="(character, index) in characters" :key="index">
                <v-btn
                  tile
                  class="text-center ma-2"
                  title
                  width="150"
                  v-if="character.type1_heroes === 'Carry' || character.type2_heroes === 'Carry'"
                >
                  <v-card-btn>
                    <v-img :src="character.img_small" max-width="36" style="margin-right: 25px;"></v-img>
                  </v-card-btn>
                  <v-card-btn>{{character.name}}</v-card-btn>
                </v-btn>
              </div>
            </v-row>
          </v-flex>
          <!------------------------------------------------------------------------>
          <!----------------------- หน้า Tank --------------------------------------->
          <!------------------------------------------------------------------------>
          <v-flex transparent-body v-if="tabname.text === 'Tank'">
            <v-row justify="center">
              <div style="margin-left: 25px;" v-for="(character, index) in characters" :key="index">
                <v-btn
                  tile
                  class="text-center ma-2"
                  title
                  width="150"
                  v-if="character.type1_heroes === 'Tank' || character.type2_heroes === 'Tank'"
                >
                  <v-card-btn>
                    <v-img :src="character.img_small" max-width="36" style="margin-right: 25px;"></v-img>
                  </v-card-btn>
                  <v-card-btn>{{character.name}}</v-card-btn>
                </v-btn>
              </div>
            </v-row>
          </v-flex>
          <!------------------------------------------------------------------------>
          <!----------------------- หน้า Support ------------------------------------>
          <!------------------------------------------------------------------------>
          <v-flex transparent-body v-if="tabname.text === 'Support'">
            <v-row justify="center">
              <div style="margin-left: 25px;" v-for="(character, index) in characters" :key="index">
                <v-btn
                  tile
                  class="text-center ma-2"
                  title
                  width="150"
                  v-if="character.type1_heroes === 'Support' || character.type2_heroes === 'Support'"
                >
                  <v-card-btn>
                    <v-img :src="character.img_small" max-width="36" style="margin-right: 25px;"></v-img>
                  </v-card-btn>
                  <v-card-btn>{{character.name}}</v-card-btn>
                </v-btn>
              </div>
            </v-row>
          </v-flex>
        </v-tab-item>
      </v-tabs-items>
      <div class="col-md-6">
          <router-view @refreshData="refreshList"></router-view>
      </div>
    </v-card>
  </v-container>
</template>
<script>
import http from "../http-common";
export default {
  name: "character-list",
  data() {
    return {
      tab: null,
      tabnames: [
        { avatar: "", text: "All Heroes" },
        { avatar: "assasin.png", text: "Assassin" },
        { avatar: "fighter.png", text: "Fighter" },
        { avatar: "mage.png", text: "Mage" },
        { avatar: "carry.png", text: "Carry" },
        { avatar: "tank.png", text: "Tank" },
        { avatar: "support.png", text: "Support" },
      ],
      characters: [],
    };
  },

  methods: {
    /* eslint-disable no-console */
    retrieveCharacters() {
      http
        .get("/character")
        .then((response) => {
          this.characters = response.data; // JSON are parsed automatically.
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveCharacters();
    },
    /* eslint-enable no-console */
  },
  mounted() {
    this.retrieveCharacters();
  },
};
</script>
