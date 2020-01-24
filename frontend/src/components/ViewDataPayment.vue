<template>
	<v-container>
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
        <v-col cols="5">
            <v-row justify="center">
                <v-col cols="10">
                    <v-text-field 
                        label="กรุณากรอกชื่อลูกค้า" 
                        solo
                        v-model="search" 
                        :rules="[(v) => !!v || 'กรุณากรอกชื่อลูกค้า']" 
                        required>
                    </v-text-field>
                </v-col>
            </v-row>
        </v-col>

        <v-btn @click="Checkdata" depressed large color="green">Search</v-btn>

             
  

      <v-data-table
        :headers="headers"
        :items="items"
        :items-page="5"
        class="elevation-1"
      ></v-data-table>

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
     
	
    /* eslint-disable no-console */    
  },
  
};
</script>