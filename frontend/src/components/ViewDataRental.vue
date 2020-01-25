<template>
<v-container fluid class="application">
    <v-layout text-center>
        <v-flex>
            <br />
            <h1>ค้นหาและแสดงข้อมูลประวัติการเช่ารถ</h1>
        </v-flex>
    </v-layout>

    <v-row justify="center">
        <v-col cols="5">
            <v-row justify="center">
                <v-col cols="10">
                    <v-text-field label="กรอกชื่อลูกค้า" solo v-model="customerNamecheck" :rules="[(v) => !!v || 'กรุณากรอกชื่อ']" required>
                    </v-text-field>
                </v-col>
                <v-col cols="2">
                    <div class="my-1">
                        <v-btn @click="findCustomer" v-on="on" depressed large color="primary">Search</v-btn>
                        <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                            <div v-if="checkSave==true" class="py-3">ค้นหาสำเร็จ</div>
                            <div v-if="checkSave==false" class="py-3">ชื่อไม่ถูกต้องกรุณากรอกใหม่</div>
                            <v-btn color="red" text @click="alwayselect = false">
                                Close
                            </v-btn>
                        </v-snackbar>
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
</v-container>
</template>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Kanit");

.application {
    font-family: "Kanit";
}
</style>
<script>
import http from "../http-common";

export default {
    name: "ViewRentalData",
    data() {
        return {
            vertical:true,
            timeout: 6000,
            checkSave: false,
            alwayselect: false,
            customerNamecheck: "",
            headers: [{
                    text: "ชื่อลูกค้า",
                    align: "left",
                    sortable: false,
                    value: "rentalBy.name"
                },
                {
                    text: "ชื่อรถ",
                    value: "selectcar.carbrand.brand"
                },
                {
                    text: "ผู้ให้เช่า",
                    value: "createdby.name"
                },
                {
                    text: "ประเภทการเช่า",
                    value: "type.nametype"
                },
                {
                    text: "จำนวนวันหรือเดือนที่เช่า",
                    value: "rentday"
                },
                {
                    text: "ราคา",
                    value: "price"
                },
                {
                    text: "วันที่เช่า",
                    value: "rentDate"
                }
            ],
            items: [],
            customerCheck: false,
            customerId: ""
        };
    },
    methods: {
        /* eslint-disable no-console */
        getRentCars() {
            http
                .get("/rentCar/" + this.customerId)
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
                .get("/customer/" + this.customerNamecheck)
                .then(response => {
                    this.customerId = response.data[0].id;
                    this.customerCheck = response.status;
                    this.getRentCars();
                    this.alwayselect = true;
                    this.checkSave = true;
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
        }
        /* eslint-disable no-console */
    },
};
</script>
