<template>
  <div v-if="this.character">
    <v-container class="application">
      <div class="text-center">
        <h2>{{this.character.name}}</h2>
      </div>
      <v-row justify="space-around">
        <v-col cols="5" justify="center">
          <div class="show-desktop"></div>
            <v-img :src="character.img_big" aspect-ratio="1"></v-img>
        </v-col>
        <v-col cols="5" justify="center">
          <v-card width="400" class="col-md" style="background: #263238;">
            <div>
              <v-card
                width="400"
                outlined
                justify="center"
                target="_self"
                style="background: #37474F;"
              >
                <v-list-item class="col-md-12" two-line>
                  <v-img style="margin-left: 25px;" :src="character.img_small" max-width="50"></v-img>
                  <span
                    justify="center"
                    style="margin-left: 25px;"
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.type1_heroes}}</span>
                  <span>
                    <v-img :src="character.img_type1_heroes" max-width="20" right></v-img>
                  </span>
                  <span
                    justify="center"
                    style="margin-left: 25px;"
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.type2_heroes}}</span>
                  <span>
                    <v-img :src="character.img_type1_heroes" max-width="20" right></v-img>
                  </span>
                </v-list-item>
              </v-card>
              <!--สกิลตัวตัว-->
              <v-list-item three-line>
                <v-list-item-avatar size="60">
                  <v-img :src="character.img_skill"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.name_skill}}</v-list-item-title>
                  <p class="text-caption" :class="`${fgColor}--text`">{{character.detail_skill}}</p>
                </v-list-item-content>
              </v-list-item>
              <v-divider></v-divider>

              <!--สกิล 1-->
              <v-list-item three-line>
                <v-list-item-avatar size="60">
                  <v-img :src="character.img_skill_1_1"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.name_skill_1_1}}</v-list-item-title>
                  <p class="text-caption" :class="`${fgColor}--text`">{{character.detail_skill_1_1}}</p>
                </v-list-item-content>
              </v-list-item>
              <v-divider></v-divider>

              <v-list-item three-line v-if="character.name_skill_1_2 != null">
                <v-list-item-avatar size="60">
                  <v-img :src="character.img_skill_1_2"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.name_skill_1_2}}</v-list-item-title>
                  <p class="text-caption" :class="`${fgColor}--text`">{{character.detail_skill_1_2}}</p>
                </v-list-item-content>
              </v-list-item>
              <v-divider></v-divider>

              <!--สกิล 2-->
              <v-list-item three-line>
                <v-list-item-avatar size="60">
                  <v-img :src="character.img_skill_2_1"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.name_skill_2_1}}</v-list-item-title>
                  <p class="text-caption" :class="`${fgColor}--text`">{{character.detail_skill_2_1}}</p>
                </v-list-item-content>
              </v-list-item>
              <v-divider></v-divider>

              <v-list-item three-line v-if="character.name_skill_2_2 != null">
                <v-list-item-avatar size="60">
                  <v-img :src="character.img_skill_2_2"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.name_skill_2_2}}</v-list-item-title>
                  <p class="text-caption" :class="`${fgColor}--text`">{{character.detail_skill_2_2}}</p>
                </v-list-item-content>
              </v-list-item>
              <v-divider></v-divider>

              <!--สกิล อัลติ-->
              <v-list-item three-line>
                <v-list-item-avatar size="60">
                  <v-img :src="character.img_skill_3_1"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.name_skill_3_1}}</v-list-item-title>
                  <p class="text-caption" :class="`${fgColor}--text`">{{character.detail_skill_3_1}}</p>
                </v-list-item-content>
              </v-list-item>

              <v-list-item three-line v-if="character.name_skill_3_2 != null">
                <v-list-item-avatar size="60">
                  <v-img :src="character.img_skill_3_2"></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="font-weight-black"
                    :class="`${bgColor}--text`"
                  >{{character.name_skill_3_2}}</v-list-item-title>
                  <p class="text-caption" :class="`${fgColor}--text`">{{character.detail_skill_3_2}}</p>
                </v-list-item-content>
              </v-list-item>
              <v-divider></v-divider>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
<script>
export default {
  name: "character",
  props: ["character"],



};
</script>