<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-1" ></h1>
      </v-flex>
    </v-layout>
   
    <v-card max-width="600" class="mx-auto">
        <br><h1 align="center">กรุณาเลือกทำรายการ</h1><br><br>
                 <v-row>
                    <v-col class="text-center">
                        <img src="../assets/save.png" style="width:100px;height:100px;"><br>
                        <v-btn rounded color="green" dark href="/maintenance" target="_self" >บันทึกข้อมูลการซ่อมบำรุง</v-btn>
                    </v-col>
        
                    <v-col class="text-center">
                        <img src="../assets/car-history.png" style="width:100px;height:100px;"><br>
                        <v-btn rounded color="green" dark href="/Maintenance_History" target="_self" >ค้นหาประวัติการซ่อมบำรุง</v-btn><br><br>
                    </v-col>
                </v-row>
    </v-card>
  </v-container>
</template>
