<template>
  <v-container class="application">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-1" ></h1>
      </v-flex>
    </v-layout>
   
      <v-card
      max-width="700"
      class="mx-auto"
    >
      
      <v-row>
                    <v-col class="text-center">
                        <p></p><font><p class="application font-weight-black display-2 ">พนักงาน</p></font><p></p><br>
                        <img src="../assets/employees.png" style="width:200px;height:200px;">
                        <v-btn rounded color="success" dark href="/login" target="_self" >เข้าสู่ระบบในฐานะพนักงาน</v-btn>

                    </v-col>
                
                    <v-col class="text-center">
                        <p></p><font><p class="text-center font-weight-black display-2 ">ช่างซ่อมรถ</p></font><p></p><br>
                        <img src="../assets/repairman.png" style="width:200px;height:200px;">
                        <v-btn rounded color="success" dark href="/loginm" target="_self" >เข้าสู่ระบบในฐานะช่างซ่อมบำรุง</v-btn>
                    </v-col>
                </v-row>
      
  
     
  
      <v-card-actions>
        
         
       
        
      </v-card-actions>
    </v-card>

  </v-container>

</template>

<script>


</script>
