<template>
  <v-container fluid class="application">
   <v-row justify="center" >
        <v-col cols="4">
        <br />
      <v-text-field
        solo
        v-model="search"
        label="เลขทะเบียนรถ"
        single-line
        hide-details
      ></v-text-field>  
      
    </v-col>
    </v-row>
    <v-row justify='center'>
<v-btn color="green" @click="Check">ค้นหา</v-btn>
 <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                            <div v-if="checkSave==true" class="py-3">ค้นหาสำเร็จ</div>
                            <div v-if="checkSave==false" class="py-3">ป้ายทะเบียนไม่ถูกต้องกรุณากรอกใหม่</div>
                            <v-btn color="red" text @click="alwayselect = false">
                                Close
                            </v-btn>
                        </v-snackbar>
</v-row>
<br/><br/>
<v-row justify="center">
  <v-card>
    <v-card-title>
      ข้อมูลรถ
      
   
    </v-card-title>

   <v-data-table
      :headers="headers" 
      :items="items"
      
      :items-per-page="5"
      class="elevation-1"
   
    ></v-data-table>
  


  </v-card>
</v-row>
   
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "CarReturnData",
  data() {
    return {
     checkSave: false,
    alwayselect:false,
    search: '',
      headers: [
        { text: "ลำดับที่",sortable: false, value: "id" },
        { text: "เลขตัวถัง",sortable: false, value: "vin" },
        { text: "ทะเบียนรถ",sortable: false, value: "plate" },
        { text: "จังหวัด",sortable: false, value: "plateprovince.province" },
        { text: "ยี่ห้อ",sortable: false, value: "carbrand.brand" },
        { text: "จำนวนที่นั่ง",sortable: false, value: "carseat.carseat" },
       
      ],

      items: []

    };
  },

  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล ReturnsCar ใส่ combobox
    Check() {
      http
        .get("/car/"+this.search)
        .then(response => {
          this.items = response.data;
          if(this.items.length == 0){
          this.alwayselect = true;
            this.checkSave = false;
            }
          else {
          this.alwayselect = true;
            this.checkSave = true;
          }
            console.log(this.items);
          
        })
        .catch(e => {
          console.log(e);
          this.alwayselect = true;
            this.checkSave = true;
        });
    },

    

    /* eslint-disable no-console */
  },


};
</script>
