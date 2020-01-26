<template>
  <v-form>
    <div>
      <v-btn rounded color="black" dark href="/Maintenance_Options" target="_self">กลับสู่หน้าเมนู</v-btn>
      <v-row justify="center">
        <h1 align="center">ระบบซ่อมบำรุง</h1>
      </v-row>

      <v-row justify="center">
        <v-col cols="2">
          <v-text-field solo readonly label="วันที่ทำรายการ" v-model="maintenance.date" required></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col sm="3">
          <v-select
            label="เลือกรถ"
            solo
            v-model="maintenance.carId"
            :items="cars"
            item-text="carbrand.brand"
            item-value="id"
            :rules="[(v) => !!v || 'กรุณาเลือกรถ']"
            required
          ></v-select>
        </v-col>

        <v-col class="d-flex" sm="2">
          <v-select
            label="ทะเบียน"
            solo
            v-model="maintenance.carId"
            :items="cars"
            item-text="plate"
            item-value="id"
            :rules="[(v) => !!v || 'ระบุทะเบียน']"
            required
          ></v-select>
        </v-col>

        <v-col class="d-flex" sm="2">
          <v-select
            label="จังหวัด"
            solo
            v-model="maintenance.carId"
            :items="cars"
            item-text="plateprovince.province"
            item-value="id"
            :rules="[(v) => !!v || 'ระบุจังหวัด']"
            required
          ></v-select>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col class="d-flex" sm="4">
          <v-select
            label="เลือกประเภทการซ่อมบำรุง"
            solo
            v-model="maintenance.serviceId"
            :items="services"
            item-text="service"
            item-value="id"
            :rules="[(v) => !!v || 'เลือกประเภทการซ่อมบำรุง']"
            required
          ></v-select>
        </v-col>

        <v-col sm="3">
          <v-text-field
            v-model="maintenance.mileage"
            label="ระยะที่เช็ค"
            prefix="กม.ที่ "
            outlined
            clearable
            hint="โปรดกรอกระยะทางป็นตัวเลข"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col sm="4">
          <v-text-field
            v-model="maintenance.messagebox"
            label="รายละเอียดการซ่อมบำรุง"
            outlined
            clearable
          ></v-text-field>
        </v-col>
        <v-col sm="3">
          <v-text-field
            v-model="maintenance.autopart"
            label="ชิ้นส่วน/อะไหล่ ที่เปลี่ยน"
            outlined
            clearable
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col class="d-flex" cols="50" sm="30">
          <v-bottom-sheet v-model="alwayselect">
            <template v-slot:activator="{ on }">
              <v-btn @click="saveMaintenance" :class="{ green: !valid, blue: valid }">submit</v-btn>
            </template>
            <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                <div v-if="checkSave==true" class="py-3">บันทึกสำเร็จ</div>
                <div v-if="checkSave==false" class="py-3">ข้อมูลไม่ถูกต้องกรุณากรอกใหม่</div>
                <v-btn color="red" text @click="alwayselect = false">Close</v-btn>
              </v-snackbar>
          </v-bottom-sheet>

          <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
        </v-col>
        <br />
        <br />
      </v-row>
      <br />
    </div>
  </v-form>
</template >

<script>
import http from "../http-common";

export default {
  name: "Maintenance",
  data() {
    return {
      checkSave: false,
      alwayselect: false,
      maintenance: {
        carId: "",
        serviceId: "",
        messagebox: "-",
        autopart: "-",
        date: new Date(),
        fixdate: ""
      },
      valid: false,

      cars: [],
      services: []
    };
  },
  methods: {
    /* eslint-disable no-console */

    getCars() {
      http
        .get("/car")
        .then(response => {
          this.cars = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getServices() {
      http
        .get("/service")
        .then(response => {
          this.services = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveMaintenance() {
      http
        .post(
          "/maintenance/" +
            this.maintenance.messagebox +
            "/" +
            this.maintenance.autopart +
            "/" +
            this.maintenance.mileage +
            "/" +
            localStorage.getItem("Id") +
            "/" +
            this.maintenance.carId +
            "/" +
            this.maintenance.serviceId
        )
        .then(response => {
          console.log(response);
          this.alwayselect = true;
          this.checkSave = true;
          this.clear();
        })
        .catch(e => {
          console.log(e);
          this.alwayselect = true;
          this.checkSave = false;
          this.clear();
        });
    },
    clear() {
      this.maintenance.carId = "";
      this.maintenance.serviceId = "";
      this.maintenance.messagebox = "";
      this.maintenance.autopart = "";
      this.maintenance.mileage = "";
    },
    refreshList() {
      this.getCars();
      this.getServices();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getCars();
    this.getServices();
  }
};
</script>