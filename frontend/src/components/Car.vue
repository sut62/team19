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
                <v-bottom-sheet v-model="alwayselect" >
                                <template v-slot:activator="{ on }">
                                    <v-btn @click="saveCar" :class="{ white: !valid, green: valid }" class="subtitle-1 font-weight-bold">ยืนยัน</v-btn>
                                </template>
                                <v-sheet class="text-center" height="200px">
                                    <div v-if="checkSave==true" class="py-3">บันทึกสำเร็จ</div>
                                    <div v-if="checkSave==false" class="py-3">ข้อมูลไม่ถูกต้องกรุณากรอกใหม่</div>
                                </v-sheet>
                            </v-bottom-sheet>
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
    checkSave: false,
    alwayselect:false,
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
            this.alwayselect = true;
            this.checkSave = true;
          this.clear()
          
        })
        .catch(e => {
            console.log(e);
            this.alwayselect = true;
            this.checkSave = false;
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
