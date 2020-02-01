<template>
<v-form>
  <v-container class="application">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <h1>สมัครสมาชิก</h1>
      </v-flex>
    </v-layout>


    <v-row justify="center" >
        <v-form v-model="valid" ref="form" >
            <v-row >
              <v-text-field
                solo
                label="ชื่อ - นามสกุล"
                v-model="name"
                :rules="[(v) => !!v || 'Item is required']"
                required
                clearable
              ></v-text-field >

              <v-text-field style="margin-left: 15px;"
                 solo
                 label="เลขบัตรประชาชน"
                 v-model="card_num"
                 :rules="[(v) => !!v || 'Item is required']"
                 required
                 clearable
              ></v-text-field >
            </v-row>
            
            <v-row>
              <v-select 
                label="อาชีพ"
                solo
                v-model="customer.jobId"
                :items="jobs"
                item-text="job"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
                <v-text-field style="margin-left: 15px;"
                  solo
                  label="อายุ"
                  v-model="age"
                  suffix = "ปี"
                  clearable
                ></v-text-field >
            </v-row>

            <v-row>
                <v-text-field
                   solo
                   label="ที่อยู่"
                   v-model="address"
                   append-outer-icon = "mdi-map-marker"
                   clearable
                ></v-text-field >
            </v-row>

            <v-row>

                <v-text-field
                  solo
                  label="เบอร์โทรศัพท์"
                  v-model="tel"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  clearable
                  ></v-text-field >

                <v-select style="margin-left: 15px;"
                  label="ประเภทการสมัคร"
                  solo
                  v-model="customer.regisTypeId"
                  :items="regisTypes"
                  item-text="type"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
            </v-row>
  
            <v-row justify="center">
                      <v-btn @click="saveCustomer" :class="ma-2" >สมัคร
                        <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
                      </v-btn>
                  <v-snackbar v-model="alwayselect" >
                      <div v-if="checkSave==true" class="py-3">บันทึกสำเร็จ</div>
                      <div v-if="checkSave==false" class="py-3">บันทึกไม่สำเร็จ</div>
                      <v-btn color="red" text @click="alwayselect = false">Close</v-btn>
                  </v-snackbar>
              <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
            </v-row>
        </v-form>
    </v-row>
  </v-container>
  </v-form>
</template>

<script>
import http from "../http-common";
export default {

  name: "customer",
  data() {
    return {
      customer: {
        jobId: "",
        regisTypeId: "",
        employeeId: "",
        nameLog:"",
        nameLogId:""
      },
      jobs:[],
      regisTypes:[],
      employees:[],
      isLogins:[],
      valid: false,
      checkSave: false,
      alwayselect:false
    };
  },
  methods: {
    
    /* eslint-disable no-console */
    // ดึงข้อมูล Job ใส่ combobox
    getJobs() {
      http
        .get("/job")
        .then(response => {
          this.jobs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

    },
    getRegisTypes() {
       http
         .get("/regisType")
         .then(response => {
           this.regisTypes = response.data;
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
    getIsLogins() {
      http
        .get("/isLogin")
        .then(response => {
          this.isLogins = response.data;
          console.log(response.data);
          this.Login()
        })
        .catch(e => {
          console.log(e);
        });
    },
    Login() {
    for ( let j in this.employees) {
      for ( let i in this.isLogins) {
        if(this.isLogins[i].username == this.employees[j].username)
        this.customer.nameLog = this.employees[j].name;
        this.customer.nameLogId = this.isLogins[i].id;
        this.customer.employeeId = this.employees[j].id;
        }
     }
    },
    Logout(){
      http
        .delete(
          "/isLogin/" +
            this.customer.nameLogId
        )
        .then(response => {
          console.log(response);
          this.$router.push("/");
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่ม submit
    saveCustomer() {
      http
        .post(
          "/customer/" +
            this.name +
            "/" +
            this.card_num +
            "/" +
            this.customer.jobId +
            "/" +
            this.age +
            "/" +
            this.address +
            "/" +
            this.tel +
            "/" +
            this.customer.regisTypeId +
            "/" +
            localStorage.getItem("Id"),
          this.customer
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
      this.getJobs();
      this.getRegisTypes();
      this.getEmployees();
      this.getIsLogins();
    }
    /* eslint-enable no-console */
  },

  mounted() {
    this.getJobs();
    this.getRegisTypes();
    this.getEmployees();
    this.getIsLogins();
  }
};
</script>