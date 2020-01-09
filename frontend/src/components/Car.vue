<template>
<v-form>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-1">เพิ่มรถเช่า</h1>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field

                solo
                label="เลขตัวถังรถ"
                v-model="car.vin"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="2">
            </v-col>
          </v-row>
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                   
                solo
                label="ทะเบียนรถ"
                v-model = "car.plate"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="2">
            </v-col>
          </v-row>
          <v-row>
              <v-col cols="10">
                <v-select
                  label="จังหวัดป้ายทะเบียน"
                  
                  v-model="car.provinceId"
                  :items="provinces"
                  item-text="province"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  solo
                ></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="10">
                <v-select
                  label="ยี่ห้อรถ"
                  
                  v-model="car.brandId"
                  :items="brands"
                  item-text="brand"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  solo
                ></v-select>
              </v-col>
            </v-row>

            <v-row>


              <v-col cols="10">
                <v-select
                  label="จำนวนที่นั่ง"
                  
                  v-model="car.carseatId"
                  :items="carseats"
                  item-text="carseat"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  solo
                ></v-select>
              </v-col>
            </v-row>

            <v-row justify="center">
               <v-col cols="8">
                <v-btn @click="saveCar">ยืนยัน</v-btn>
                <v-btn style="margin-left: 2px;" @click="clear">clear</v-btn>
              </v-col>
            </v-row>
            <br />
          
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</v-form>
</template>

<script>
import http from "../http-common";

export default {
  name: "car",
  data() {
    return {
      car: {
        vin: "",
        plate :"",
        carseatId: "",
        brandId: "",
        provinceId: "",
        
      },
      vin:[],
      plate:[],
      carseats:[],
      brands:[],
      provinces:[],
      valid: false
    };
  },
  methods: {
    /* eslint-disable no-console */

    getCarseats() {
      http
        .get("/carseat")
        .then(response => {
          this.carseats = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getBrands() {
      http
        .get("/brand")
        .then(response => {
          this.brands = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
     getProvinces() {
      http
        .get("/province")
        .then(response => {
          this.provinces = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
     
    saveCar() {
      http
        .post(
          "/car/" +
            this.car.vin + "/" 
            +
            this.car.plate + "/"
             +
            this.car.carseatId +
            "/" +
            this.car.brandId +
            "/" +
            this.car.provinceId +
            "/" +
            localStorage.getItem("Id"),
          this.car
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ");
          this.clear()
          
        })
        .catch(e => {
            console.log(e);
            alert("บันทึกไม่สำเร็จ");
            this.clear();
        });
    },
    clear() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getCarseats();
      this.getBrands();
      this.getProvinces();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getBrands();
    this.getCarseats();
     this.getProvinces();
  }
};
</script>
