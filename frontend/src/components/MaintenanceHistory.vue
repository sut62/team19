<template>
  <v-container fluid class="application">
    <v-row justify="center">
      <v-col cols="5">
        <v-row justify="center">
          <v-col cols="10">
            <div>{{checkSave}}</div>
            <v-text-field
              label="กรุณากรอกทะเบียนรถ"
              solo
              v-model="search"
              :rules="[(v) => !!v || 'กรุณากรอกทะเบียนรถ']"
              required
            ></v-text-field>
          </v-col>
          <v-col cols="2">
            <div class="my-1">
              <v-btn @click="findCustomer" v-on="on" depressed large color="primary">Search</v-btn>
              <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                <div v-if="checkSave==true" class="py-3">ค้นหาสำเร็จ</div>
                <div v-if="checkSave==false" class="py-3">ชื่อไม่ถูกต้องกรุณากรอกใหม่</div>
                <v-btn color="red" text @click="alwayselect = false">Close</v-btn>
              </v-snackbar>
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <br />
    <br />
    <v-row justify="center">
      <v-card>
        <v-card-title>ประวัติการซ่อมบำรุงรถ</v-card-title>
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
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
      vertical: true,
      timeout: 6000,
      checkSave: false,
      alwayselect: false,
      search: "",
      headers: [
        { text: "ช่างซ่อม", value: "fixedBy.repairman" },
        { text: "รถ", value: "car.carbrand.brand" },
        { text: "ทะเบียน", value: "car.plate" },
        { text: "จังหวัด", value: "car.plateprovince.province" },
        { text: "การซ่อม", value: "service.service" },
        { text: "รายละเอียดการซ่อม", value: "messageBox" },
        { text: "อะไหล่/ชิ้นส่วน ที่เปลี่ยน", value: "autoPart" },
        { text: "ระยะ", value: "mileage" },
        { text: "วันที่", value: "fixdate" }
      ],

      items: []
    };
  },

  methods: {
    /* eslint-disable no-console */
    //ดึงข้อมูล RentCars ใส่ combobox
    getMaintenance() {
      http
        .get("/maintenance/" + this.search)
        .then(response => {
          this.items = response.data;
          console.log(this.items);
          this.alwayselect = true;
          this.checkSave = true;
        })
        .catch(e => {
          console.log(e);
          this.alwayselect = true;
          this.checkSave = false;
        });
    }
    /* eslint-disable no-console */
  }
};
</script>
