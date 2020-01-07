<template>
<v-form>
  <v-container>
    <v-layout text-center wrap>
      <v-flex>
        <h1 class="display-2 font-weight-bold">เช่ารถ</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center" >
      <v-col cols="9" >
        <v-form v-model="valid" ref="form">

          <v-row justify="center">
          <v-col cols="10">
            <v-select class="subtitle-1 font-weight-bold"
              label="เลือกลูกค้า"
              solo
              v-model="rentCar.customerId"
              :items="customers"
              item-text="name"
              item-value="id"
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-select>
          </v-col>
          </v-row>

          <v-row justify="center">
            <v-select class="subtitle-1 font-weight-bold"
              label="เลือกประเภทการเช่า"
              solo
              v-model="rentCar.rentType"
              :items="rentTypes"
              item-text="nametype"
              item-value="id"
              :rules="[(v) => !!v || 'Item is required']"
              required
              @input="getPrice"
            ></v-select>

            <v-text-field  style="margin-left: 10px;" class="subtitle-1 font-weight-bold"
              solo
              readonly
              label="ราคาต่อ วัน/เดือน"
              v-model="rentCar.rentTypePrice"
              @input="calculate"
              ></v-text-field>
          </v-row>

            <v-row justify="center">
                  <v-col cols="10">
                    <v-select class="subtitle-1 font-weight-bold"
                      label="เลือกรถ"
                      solo
                      v-model="rentCar.carId"
                      :items="cars"
                      item-text="carbrand.name"
                      item-value="id"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
                    ></v-select>
                  </v-col>
              </v-row>

                <v-row justify="center"> 
                  <v-text-field   class="subtitle-1 font-weight-bold"
                    solo
                    label="จำนวนที่ต้องการเช่า"
                    v-model="rentCar.rentday"
                    suffix="วัน/เดือน"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                    @input="calculate"
                  ></v-text-field>

                  <v-text-field style="margin-left: 15px;" class="subtitle-1 font-weight-bold"
                  readonly
                  solo
                  label="ราคาทั้งหมด"
                  v-model="rentCar.price"
                  suffix="บาท"
                  ></v-text-field>
                </v-row>

                <v-row justify="center">
                <v-col class="text-center" cols="12">
                <v-btn @click="saveCarRent" :class="{ white: !valid, green: valid }" 
                class="subtitle-1 font-weight-bold">ยืนยัน</v-btn>

                <v-btn style="margin-left: 15px;" @click="clear" class="subtitle-1 font-weight-bold" >ล้างข้อมูล</v-btn>
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
  name: "rentCar",
  data() {
    return {
      rentCar: {
        rentTypeId: '',
        customerId: "",
        carId: "",
        employeeId: "",
        rentday:"",
        price:"",
        rentTypePrice: '',
        rentType:""
      },
      valid: false,
      customers:[],
      employees:[],
      cars:[],
      rentTypes:[]
    };
  },

  methods: {
  getPrice() {
    for ( let i in this.rentTypes) {
    if (this.rentCar.rentType == this.rentTypes[i].id) {
      this.rentCar.rentTypeId = this.rentTypes[i].id;
      this.rentCar.rentTypePrice = this.rentTypes[i].unitprice;
     }
    }
  },
    /* eslint-disable no-console */
    ////////////////////////##[ calculate ]##////////
    calculate(){
        this.rentCar.price = this.rentCar.rentday*this.rentCar.rentTypePrice;
    },
    ////////////////////////////////////////////////
    // ดึงข้อมูล Customer ใส่ combobox
    getCustomers() {
      http
        .get("/customer")
        .then(response => {
          this.customers = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล RentType ใส่ combobox
    getRentTypes() {
      http
        .get("/rentType")
        .then(response => {
          this.rentTypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Car ใส่ combobox
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
    // ดึงข้อมูล Employee ใส่ combobox
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function เมื่อกดปุ่ม submit
    saveCarRent() {
      http
        .post(
          "/rentCar/" +
            this.rentCar.rentday +
            "/" +
            this.rentCar.rentType +
            "/" +
            this.rentCar.customerId +
            "/" +
            this.rentCar.carId +
            "/" +
            localStorage.getItem("Id") +
            "/" +
            this.rentCar.rentTypePrice,
          this.rentCar
        )
        .then(response => {
          console.log(response);
          alert("บันทึกสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("บันทึกไม่สำเร็จ");
        });
    },
     clear() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getCustomers();
      this.getCars();
      this.getEmployees();
      this.getRentTypes();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getCustomers();
    this.getCars();
    this.getEmployees();
    this.getRentTypes();
  }
};
</script>
