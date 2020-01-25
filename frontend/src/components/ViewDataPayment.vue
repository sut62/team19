<template>
	<v-container class = "application">
		<br/><br/>
	<v-card
    class="mx-auto"
    max-width="1000" >
    <v-img
      src="https://www.sabre.com/wp/wp-content/uploads/shutterstock_248825623-e1460664714802-720x415.jpg"
      height="270px"
    ></v-img>

    <v-card-title>
      ข้อมูลการชำระเงิน
    </v-card-title>

    <v-layout text-center wrap>

      
    <v-row justify="space-around">
      <v-col cols = "30" sm = "30">
        <v-img
      src="https://image.flaticon.com/icons/svg/477/477833.svg" 
      height="100px"
      max-width="100px"
      ></v-img>
    </v-col>
</v-row>

    
        
        <v-col cols="10">
            <v-row justify="center">

                <v-col cols = "12" sm = "6" >
                    <v-text-field label="กรอกชื่อลูกค้า" solo v-model="search" :rules="[(v) => !!v || 'กรุณากรอกชื่อ']" required>
                    </v-text-field>
                </v-col>
                <v-col cols="2">
                    <div class="my-1">
                        <v-btn @click="findCustomer" depressed large color="#CDDC39">Search</v-btn>
                         <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                            <div v-if="checkSave==true" class="py-3">ค้นหาสำเร็จ</div>
                            <div v-if="checkSave==false" class="py-3">ชื่อไม่ถูกต้องกรุณากรอกใหม่</div>
                            <v-btn color="red" text @click="alwayselect = false">
                                ปิด
                            </v-btn>
                        </v-snackbar>
                    </div>
                </v-col>
            </v-row>
        </v-col>
   


      <v-row justify="center">
      <v-col cols="10">
      <v-data-table
        :headers="headers"
        :items="items" 
        :items-per-page="5"
        class="elevation-1"
      ></v-data-table>
    </v-col>
  </v-row>



 </v-layout> 

  </v-card>
</v-container>
</template>







<script>
import http from "../http-common";

export default {
  name: "ViewDataPayment",
  data() {
	return {
      search: '',
      vertical:true,
      timeout: 9000,
      alwayselect: false,
      checkSave: false,
      headers: [
    { text: "ลำดับที่",sortable: false, value: "id" },
		{ text: "ชื่อลูกค้า",sortable: false, value: "rent.rentalBy.name" },
		{ text: "ชื่อรุ่นรถ",sortable: false, value: "rent.selectcar.carbrand.brand" },
		{ text: "วันที่่เช่ารถ",sortable: false, value: "rent.rentDate" },
		{ text: "ชื่อพนักงานที่ชำระเงิน",sortable: false, value: "createdby.name" },
		{ text: "วิธีการชำระเงิน",sortable: false, value: "payoptions.name" },
		{ text: "หมายเหตุ",sortable: false, value: "note" },
		{ text: "วันที่ชำระเงิน",sortable: false, value: "date"}
      ],

    items: [],
    customerId: "",
	};  
  },

    methods: {
    /* eslint-disable no-console */


    Checkdata() {
       http
        .get("/payment/" + this.search)
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