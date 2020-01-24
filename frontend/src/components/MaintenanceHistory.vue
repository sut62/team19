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
<v-btn color="green" @click="getMaintenance">ค้นหา</v-btn>
</v-row>
<br/><br/>
<v-row justify="center">
  <v-card>
    <v-card-title>
      ประวัติการซ่อมบำรุงรถ
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
  name: "MaintenanceHistory",
  data() {
    return {
    search: '',
      headers: [
        { text: "ช่างซ่อม", value: "fixedBy.repairman" },
        { text: "รถ", value: "car.carbrand.brand" },
        { text: "ทะเบียน", value: "car.plate"},
        { text: "จังหวัด", value: "car.plateprovince.province"},
        { text: "การซ่อม", value: "service.service"},
        { text: "รายละเอียดการซ่อม", value: "messageBox" },
        { text: "อะไหล่/ชิ้นส่วน ที่เปลี่ยน", value: "autoPart" },
        { text: "ระยะ", value: "mileage" },
        { text: "วันที่", value: "fixdate"},
       
      ],

      items: []

    };
  },

  methods: {
    /* eslint-disable no-console */
        // ดึงข้อมูล RentCars ใส่ combobox
        getMaintenance() {
            http
                .get("/maintenance/" + this.search)
                .then(response => {
                    this.items = response.data;
                    console.log(this.items);
                })
                .catch(e => {
                    console.log(e);
                });
        }
        /* eslint-disable no-console */
  },


};
</script>
