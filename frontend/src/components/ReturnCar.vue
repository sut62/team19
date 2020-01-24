<template>
<v-form>
   <v-container >
    <v-layout text-center wrap>
      <v-flex mb-1>
        <br />
        <h1 class="display-2 font-weight-bold mb-2">คืนรถที่เช่า</h1>
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
                  v-model="returnsCar.rentcarId"
                  hint="รายชื่อลูกค้า"
                  persistent-hint
                  prefix="คุณ "
                  :items="rentcars"
                  item-text="rentalBy.name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Select Rental is required']"
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
                            v-model="returnsCar.rentcarr"
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
                            v-model="returnsCar.rentdays"
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
                            v-model="returnsCar.renttype"
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
                            v-model="returnsCar.renttime"
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
                            v-model="returnsCar.rentprice"
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
                  label="ปรับเงิน"
                  solo
                  v-model="returnsCar.payforfineId"
                  hint="ปรับเงินกรณี"
                  persistent-hint
                  :items="payforfines"
                  item-text="description"
                  item-value="id"
                  :rules="[(v) => !!v || 'Please Select ID ']"
                  required  
                  ></v-select>
            </v-col>
          </v-row>
               
            <v-row>
              <v-col cols="10" >
                <v-text-field
                  v-model="returnsCar.note"
                   :rules="[(v) => !!v || 'Please add a message!']"

                    label="หมายเหตุ"
                    outlined
                    clearable
                ></v-text-field>
              </v-col>
            </v-row>
   

            <v-row justify="center">
              <v-col cols="15">
                 <v-bottom-sheet v-model="alwayselect" >
                                <template v-slot:activator="{ on }">
                                    <v-btn @click="saveCarReturn" :class="{ writh: !valid, green: valid }">Accept</v-btn>
                                    <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                                <div v-if="checkSave==true" class="py-3">บันทึกสำเร็จ</div>
                                <div v-if="checkSave==false" class="py-3">ข้อมูลไม่ถูกต้องกรุณากรอกใหม่</div>
                                <v-btn color="red" text @click="alwayselect = false">
                                    Close
                                </v-btn>
                            </v-snackbar>
                                </template>
                                
                  </v-bottom-sheet>

               
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
              </v-col>
            </v-row>

         </v-row>

        </v-form>
      </v-col>
    </v-row>
  </v-container>
  </v-form>
</template>

<script>
import http from "../http-common";


export default {
  name: "returnsCar",
  data () {
    return {
      returnsCar: {
        payforfineId: "",
        rentcarId: "", 
        note: "",

        rentcarr:"",
        rentcarrId:"",

        renttype:"",
        renttypeId:"",

        renttime:"",
        rentprice:"",
        rentdays:""
      
      },

      /* แสดงข้อมูลใน combobox  */
      valid: false,
      payforfines:[],
      rentcars:[],
      menu:[],
      cars:[],
      renttypes:[],
      checkSave: false,
      alwayselect:false
      
    };
  },

  methods: {
    /* eslint-disable no-console */ 

    getRent() {

  for ( let i in this.rentcars) {
    if (this.returnsCar.rentcarId == this.rentcars[i].id) {
      this.returnsCar.rentcarId = this.rentcars[i].id;
      this.returnsCar.rentprice = this.rentcars[i].price;
      this.returnsCar.renttime = this.rentcars[i].rentDate;
      this.returnsCar.rentdays = this.rentcars[i].rentday;
     }
    }

    
  for ( let i in this.rentcars) {
    if (this.returnsCar.rentcarId == this.rentcars[i].id) {
      this.returnsCar.rentcarId = this.rentcars[i].id;
   for ( let i in this.renttypes) {
    if (this.returnsCar.rentcarId != null) {
      this.returnsCar.renttypeId = this.renttypes[i].id;
      this.returnsCar.renttype = this.renttypes[i].nametype;
     }
    }
    this.returnsCar.renttype = this.rentcars[i].type.nametype;
     }
    }


  for ( let i in this.rentcars) {
    if (this.returnsCar.rentcarId == this.rentcars[i].id) {
      this.returnsCar.rentcarId = this.rentcars[i].id;
    for ( let i in this.cars) {
     if (this.returnsCar.rentcarId != null) {
      this.returnsCar.rentcarrId = this.cars[i].id;
      this.returnsCar.rentcarr = this.cars[i].name;
     }
    }
    this.returnsCar.rentcarr = this.rentcars[i].selectcar.carbrand.brand;
    }
  }
  
    },

     
    getPayforfinees() {
      http
        .get("/payforfine")
        .then(response => {
          this.payforfines = response.data;
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
  
    saveCarReturn() {
      http
        .post(
          "/returnsCar/" +
            this.returnsCar.note +
            "/" +
             localStorage.getItem("Id") +
            "/" +
            this.returnsCar.rentcarId +
            "/" +       
            this.returnsCar.payforfineId  ,
            
          this.returnsCar
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
     
      this.getPayforfinees();
      this.getRentCars();
      this.getCars();
      this.getRentalTypes();
    }
    
  },

  mounted() {
    
    this.getPayforfinees();
    this.getRentCars();
    this.getCars();
    this.getRentalTypes();
  }
};
</script>

