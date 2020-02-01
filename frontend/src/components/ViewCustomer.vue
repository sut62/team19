<template>
  <v-container class="application">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1>ข้อมูลสมาชิก</h1>
      </v-flex>
    </v-layout>

  <v-row justify="center">
      <v-col cols="4">
          <v-row justify="center">
              <v-text-field
                solo
                label="Customer Name"
                v-model="customerName"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
                      <v-btn @click="findCustomer" style="margin-left: 15px;" depressed large color="darken-2">Search
                        <v-icon color="grey darken-2">mdi-magnify</v-icon>
                      </v-btn>
                  <v-snackbar v-model="alwayselect" >
                      <div v-if="checkSearch==true" class="py-3">ค้นหาสำเร็จ</div>
                      <div v-if="checkSearch==false" class="py-3">ไม่พบข้อมูล</div>
                      <v-btn color="red" text @click="alwayselect = false">Close</v-btn>
                  </v-snackbar>
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

  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "ViewCustomer",
  data() {
    return {
      headers: [
        {
          text: "ชื่อลูกค้า",
          align: "left",
          sortable: false,
          value: "name"
        },
        { text: "เลขบัตรประจำตัว", value: "card_num" },
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
      customerCheck: false,
      checkSearch: false,
      alwayselect:false   
    };
  },

  methods: {

      /* eslint-disable no-console */
    findCustomer() {
      http
        .get("/customer/" + this.customerName)
        .then(response => {
          if (response.data[0] != null) {
            this.customerId = response.data.id;
            this.customerCheck = response.status;
            this.alwayselect = true;
            this.checkSearch = true;
            this.getCustomers();
          } else {
            this.alwayselect = true;
            this.checkSearch = false;
          }          
        })
        .catch(e => {
          console.log(e);
          this.clear();
        });
    },
    clear() {
      this.$refs.form.reset();
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