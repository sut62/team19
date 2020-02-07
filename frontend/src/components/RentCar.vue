<template>
<v-form>
    <v-container>
        <v-layout text-center wrap  class="application">
            <v-flex>
                <h1>เช่ารถ</h1>
            </v-flex>
        </v-layout>

        <v-row justify="center">
            <v-col cols="9">
                <v-form v-model="valid" ref="form">

                    <v-row justify="center">
                        <v-col cols="10">
                            <v-select class="subtitle-1 font-weight-bold" 
                                label="เลือกลูกค้า" 
                                solo 
                                v-model="rentCar.customerId" 
                                :items="customers" item-text="name" 
                                item-value="id" 
                                :rules="[(v) => !!v || 'กรุณาเลือกชื่อลูกค้า']" 
                                required>
                            </v-select>
                        </v-col>
                    </v-row>

                    <v-row justify="center">
                        <v-select class="subtitle-1 font-weight-bold" 
                            label="เลือกประเภทการเช่า" 
                            solo 
                            v-model="rentCar.rentType" 
                            :items="rentTypes" item-text="nametype" 
                            item-value="id" :rules="[(v) => !!v || 'กรุณาเลือกประเภทการเช่า']" 
                            required 
                            @input="getPrice">
                        </v-select>

                        <v-text-field style="margin-left: 10px;" 
                            class="subtitle-1 font-weight-bold" 
                            solo 
                            readonly 
                            label="ราคาต่อ วัน/เดือน" 
                            v-model="rentCar.rentTypePrice" 
                            @input="calculate">
                        </v-text-field>
                    </v-row>

                    <v-row justify="center">
                        <v-col cols="10">
                            <v-select class="subtitle-1 font-weight-bold" 
                                label="เลือกรถ" solo v-model="rentCar.carId" 
                                :items="cars" item-text="carbrand.brand" 
                                item-value="id" :rules="[(v) => !!v || 'กรุณาเลือกรถที่ต้องการเช่า']" 
                                required>
                            </v-select>
                        </v-col>
                    </v-row>

                    <v-row justify="center">
                        <v-text-field class="subtitle-1 font-weight-bold" 
                            solo 
                            label="จำนวนที่ต้องการเช่า" 
                            v-model="rentCar.rentday" 
                            suffix="วัน/เดือน" 
                            :rules="[(v) => !!v || 'กรุณากรอกจำนวนวันหรือเดือน']" 
                            required 
                            @input="calculate">
                        </v-text-field>

                        <v-text-field style="margin-left: 15px;" 
                            class="subtitle-1 font-weight-bold" 
                            readonly 
                            solo 
                            label="ราคาทั้งหมด" 
                            v-model="rentCar.price" 
                            suffix="บาท">
                        </v-text-field>
                    </v-row>

                    <v-row justify="center">
                        <v-col class="text-center" cols="12">
                            <v-btn @click="saveCarRent" 
                                        :class="{ white: !valid, green: valid }"  
                                        v-on="on" 
                                        class="subtitle-1 font-weight-bold">ยืนยัน</v-btn>
                            <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                                <div v-if="checkSave==true" class="py-3">บันทึกสำเร็จ</div>
                                <div v-if="checkSave==false" class="py-3">ข้อมูลไม่ถูกต้องกรุณากรอกใหม่</div>
                                <v-btn color="red" text @click="alwayselect = false">
                                    Close
                                </v-btn>
                            </v-snackbar>
                            <v-btn style="margin-left: 15px;" @click="clear" class="subtitle-1 font-weight-bold">ล้างข้อมูล</v-btn>
                        </v-col>
                    </v-row>

                    <br />
                </v-form>
            </v-col>
        </v-row>
    </v-container>
</v-form>
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
    name: "rentCar",
    
    data() {
        return {
            vertical:true,
            timeout: 6000,
            checkSave: false,
            alwayselect:false,
            rentCar: {
                rentTypeId: '',
                customerId: "",
                carId: "",
                employeeId: "",
                rentday: "",
                price: "",
                rentTypePrice: '',
                rentType: ""
            },
            valid: false,
            customers: [],
            employees: [],
            cars: [],
            rentTypes: []
        };
    },

    methods: {

        getPrice() {
            for (let i in this.rentTypes) {
                if (this.rentCar.rentType == this.rentTypes[i].id) {
                    this.rentCar.rentTypeId = this.rentTypes[i].id;
                    this.rentCar.rentTypePrice = this.rentTypes[i].unitprice;
                }
            }
        },
        /* eslint-disable no-console */
        calculate() {
            this.rentCar.price = this.rentCar.rentday * this.rentCar.rentTypePrice;
        },
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
