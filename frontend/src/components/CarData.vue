<template>
  <v-container>
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
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },

    

    /* eslint-disable no-console */
  },


};
</script>
