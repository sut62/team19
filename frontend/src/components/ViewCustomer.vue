<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Member Information</h1>
      </v-flex>
    </v-layout>

  <v-row justify="center">
      <v-col cols="4">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="Customer Name"
                v-model="customerName"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findCustomer" >Search</v-btn>
              </div>
            </v-col>
          </v-row>
      </v-col>
  </v-row>

      <div v-if="customerCheck">
        <v-row justify="center">
            <v-col cols="12">
                <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
                </v-data-table>
                <br />
                <v-row justify="center">
                </v-row>
            </v-col>
        </v-row>
    </div>

    <v-row justify="center">
       <v-col>
        <v-btn style="margin-left: 15px;"
        href="/cus"
        >back</v-btn>
       </v-col>
    </v-row>

  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "ViewCustomer",
  data() {
    return {
      headers: [
      //{ text: "ID", value: "id" },
        {
          text: "ชื่อลูกค้า",
          align: "left",
          sortable: false,
          value: "name"
        },
        { text: "เลขบัตรประจำตัว", value: "num_id" },
        { text: "อาชีพ", value: "work.job" },
        { text: "อายุ", value: "age" },
        { text: "ที่อยู่", value: "address" },
        { text: "เบอร์โทรศัพท์", value: "tel" },
        { text: "ประเภทการสมัคร", value: "type.type" },
        { text: "พนักงาน", value: "createdBy.name" },
        { text: "วันที่สมัคร", value: "date" }
      ],
      items: [],
      customerName: "",
      customerId: "",
      customerCheck: false
      
    };
  },

  methods: {

      /* eslint-disable no-console */
    findCustomer() {
      http
        .get("/customer/" + this.customerName)
        .then(response => {
          //this.items = response.data;
          //console.log(this.items);
          if (response.data[0] != null) {
            this.customerId = response.data.id;
            this.customerCheck = response.status;
            this.getCustomers();
          } else {
            alert("ค้นหาผิดพลาด")
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
    },
    back() {
      this.$router.push("/");
    },
    getCustomers() {
      http
        .get("/customer/" + this.customerName)
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    }

  }
};

</script>