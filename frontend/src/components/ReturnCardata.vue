<template>
  <v-container>
<v-row justify="center">
  <v-card>
    <v-alert type="success">
    <v-card-title>
      [Car Return List]
      <br>
      <br>
      <br>

      <v-text-field
        v-model="search"
        label=":"
        single-line
        hide-details
      ></v-text-field>
       <v-btn @click="findCustomer" depressed large color="green">Search</v-btn>
       <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                            <div v-if="checkSave==true" class="py-3">ค้นหาสำเร็จ</div>
                            <div v-if="checkSave==false" class="py-3">ชื่อไม่ถูกต้องกรุณากรอกใหม่</div>
                            <v-btn color="red" text @click="alwayselect = false">
                                Close
                            </v-btn>
                        </v-snackbar>
    </v-card-title>
    </v-alert>


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
    vertical:true,
    timeout: 6000,
    checkSave: false,
    alwayselect: false,
      headers: [
        { text: "ลำดับที่",sortable: false, value: "id" },
        { text: "ชื่อคุณลูกค้า",sortable: false, value: "rents.rentalBy.name" },
        { text: "ชื่อรุ่นรถ",sortable: false, value: "rents.selectcar.carbrand.brand" },
        { text: "วันที่เช่ารถ",sortable: false, value: "rents.rentDate" },
        { text: "ชื่อพนักงานทำรายการคืนรถ",sortable: false, value: "createdby.name" },
        { text: "มีการปรับเงินกรณี",sortable: false, value: "pays.description" },
        { text: "หมายเหตุ",sortable: false, value: "note" },
        { text: "วันที่ดำเนินการคืนรถ",sortable: false, value: "returndate" }
      ],

      items: [],
      customerId: "",
     
  
    };
  },

  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล ReturnsCar ใส่ combobox
    
    Checkdata() {
      http
        .get("/returnsCar/"+this.customerId)
        .then(response => {
          this.items = response.data;
           console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
      
    },
     findCustomer() {
            http
                .get("/customer/" + this.search)
                .then(response => {
                    //this.items = response.data;
                    this.customerId = response.data[0].id;
                    this.Checkdata();
                    this.alwayselect = true;
                    this.checkSave = true;
                    console.log(this.customerId);
                })
                .catch(e => {
                    this.alwayselect = true;
                    this.checkSave = false;
                    console.log(e);
                });
        },    
    /* eslint-disable no-console */
  },

};
</script>
