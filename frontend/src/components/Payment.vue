<template>
<v-form>
   <v-container >
    <v-layout text-center wrap>
      <v-flex mb-1>
        <br />
        <h1 class="display-2 font-weight-bold mb-2">ชำระเงิน</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="6" >
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
           <v-row >
              <v-col cols="10">
                <v-select
                  label="ลูกค้า"
                  solo
                  v-model="payment.rentcarId"
                  hint="รายชื่อลูกค้า"
                  persistent-hint
                  prefix="คุณ "
                  :items="rentcars"
                  item-text="rentalBy.name"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือกรายการที่ต้องการ']"
                  @input="getRent"
                  required
                ></v-select>
            </v-col>
           </v-row>



              <v-row>
               <v-col cols="10">
                 <v-text-field
                   solo
                   readonly
                   label="ชื่อรถ"
                            v-model="payment.rentcarr"
                            hint="ชื่อ/รุ่น รถยนต์"
                            persistent-hint
                            required
                 ></v-text-field>
                </v-col>
             </v-row>

             <v-row>
               <v-col cols="10">
                 <v-text-field
                   solo
                   readonly
                   label="จำนวนวันเช่า"
                            v-model="payment.rentdays"
                            hint="จำนวนวันที่เช่า"
                            persistent-hint
                            suffix="วัน/เดือน"
                            required
                 ></v-text-field>
                </v-col>
             </v-row>

             <v-row>
               <v-col cols="10">
                 <v-text-field
                   solo
                   readonly
                   label="ประเภทการเช่า"
                            v-model="payment.renttype"
                            hint="ประเภทการเช่าแบบเป็น"
                            persistent-hint
                            required
                 ></v-text-field>
                </v-col>
             </v-row>

                <v-row>
               <v-col cols="10">
                 <v-text-field
                   solo
                   readonly
                   label="เวลาเช่ารถ"
                            v-model="payment.renttime"
                            hint="วันที่เช่ารถ YYYY/MM/DD"
                            persistent-hint
                            required
                 ></v-text-field>
                </v-col>
             </v-row>

              <v-row>
               <v-col cols="10">
                 <v-text-field
                   solo
                   readonly
                   label="ราคารวม"
                            v-model="payment.rentprice"
                            hint="ราคารวมทั้งหมด"
                            persistent-hint
                            suffix="บาท"
                            required
                 ></v-text-field>
                </v-col>
             </v-row>

            <v-row >
              <v-col cols="10" >
                <v-select
                  label="วิธีชำระเงิน"
                  solo
                  v-model="payment.optionsId"
                  hint="วิธีการชำระเงิน"
                  persistent-hint
                  :items="optionss"
                  item-text="name"
                  item-value="id"
                  :rules="[(v) => !!v || 'กรุณาเลือกรายการที่ต้องการ']"
                  required
                  ></v-select>
            </v-col>
          </v-row>




            <v-row justify="center">
              <v-col cols="12">
                <v-bottom-sheet v-model="alwayselect" >
                                <template v-slot:activator="{ on }">
                                    <v-btn @click="savePayment" :class="{ pink: !valid, teal: valid }">บันทึก</v-btn>
                                </template>
                                <v-sheet class="text-center" height="200px">
                                    <div v-if="checkSave==true" class="py-3">บันทึกสำเร็จ</div>
                                    <div v-if="checkSave==false" class="py-3">ข้อมูลไม่ถูกต้องกรุณากรอกใหม่</div>
                                </v-sheet>
                            </v-bottom-sheet>

                <v-btn style="margin-left: 15px;" @click="clear" color = "yellow">clear</v-btn>
              </v-col>
            </v-row>

            </v-row>

        </v-form>
                <v-row >
                     <v-col cols="10" >
                      <v-text-field
                       color = "green"
                       solo
                       readonly
                       label="วันที่ชำระเงิน"
                       v-model="payment.date"
                       hint="วันที่ชำระเงิน"
                       persistent-hint
                       required
                       ></v-text-field>
                     </v-col>
                </v-row>
      </v-col>
    </v-row>
  </v-container>
  </v-form>
</template>

<script>
import http from "../http-common";


export default {
  name: "payment",
  data () {
    return {
      checkSave: false,
      alwayselect:false,
      payment: {
        optionsId: "",
        rentcarId: "",
        rentcusId:"",
        rentcarr:"",
        rentcarrId:"",
        renttype:"",
        renttypeId:"",
        renttime:"",
        rentprice:"",
        rentdays:"",

        date: new Date()
      },

      /* แสดงข้อมูลใน combobox  */
      valid: false,
      optionss:[],
      rentcars:[],
      menu:[],
      cars:[],
      renttypes:[]

    };
  },

  methods: {
    /* eslint-disable no-console */

    getRent() {

  for ( let i in this.rentcars) {
    if (this.payment.rentcarId == this.rentcars[i].id) {
      this.payment.rentcarId = this.rentcars[i].id;
      this.payment.rentprice = this.rentcars[i].price;
      this.payment.renttime = this.rentcars[i].rentDate;
      this.payment.rentdays = this.rentcars[i].rentday;
     }
    }

  for ( let i in this.rentcars) {
    if (this.payment.rentcarId == this.rentcars[i].id) {
      this.payment.rentcarId = this.rentcars[i].id;
   for ( let i in this.renttypes) {
    if (this.payment.rentcarId != null) {
      this.payment.renttypeId = this.renttypes[i].id;
      this.payment.renttype = this.renttypes[i].nametype;
     }
    }
    this.payment.renttype = this.rentcars[i].type.nametype;
     }
    }


  for ( let i in this.rentcars) {
    if (this.payment.rentcarId == this.rentcars[i].id) {
      this.payment.rentcarId = this.rentcars[i].id;
    for ( let i in this.cars) {
     if (this.payment.rentcarId != null) {
      this.payment.rentcarrId = this.cars[i].id;
      this.payment.rentcarr = this.cars[i].name;
     }
    }
    this.payment.rentcarr = this.rentcars[i].selectcar.carbrand.brand;
    }
  }

    },

      
    getOptionss() {
      http
        .get("/options")
        .then(response => {
          this.optionss = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    
    getRentCars() {
      http
        .get("/rentCar")
        .then(response => {
           this.rentcars = response.data;
           console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

   

    getCars() {
      http
        .get("/brand")
        .then(response => {
          this.cars = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

     getRentalTypes() {
      http
        .get("/rentType")
        .then(response => {
          this.renttypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    savePayment() {
      http
        .post(
          "/payment/" +
             localStorage.getItem("Id") +
            "/" +
            this.payment.rentcarId +
            "/" +
            this.payment.optionsId  ,

          this.payment
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
      this.$refs.form.reset();
    },

    refreshList() {
      
      this.getOptionss();
      this.getRentCars();
      this.getCars();
      this.getRentalTypes();
    }

  },

  mounted() {
  
    this.getOptionss();
    this.getRentCars();
    this.getCars();
    this.getRentalTypes();
  }
};
</script>

