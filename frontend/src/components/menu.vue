<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-1 font-weight-bold mb-1">กรุณาเลือกรายการที่ต้องการทำ</h1>
      </v-flex>
    </v-layout>
     
       <v-card
      max-width="200"
      class="mx-auto"
    >
      
      <v-row>
                    <v-col class="text-center" cols="12" sm="15">
                        <v-btn large rounded color="primary" dark href="/cus" target="_self" >สมัครสมาชิก</v-btn><br/><br/>
                   
                        <v-btn large rounded color="primary" dark href="/car" target="_self" >เพิ่มรถเช่า</v-btn><br/><br/>
                    
                        <v-btn large rounded color="primary" dark href="/carent" target="_self" >เช่ารถ</v-btn><br/><br/>
                   
                        <v-btn large rounded color="primary" dark href="/pay" target="_self" >ชำระเงิน</v-btn><br/><br/>
                   
                        <v-btn large rounded color="primary" dark href="/returncar" target="_self" >คืนรถ</v-btn>
                    </v-col>
      </v-row>
      
  
     
  
      <v-card-actions>
         </v-card-actions>
    </v-card>

  </v-container>

</template>

<script>


</script>
