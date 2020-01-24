<template>
<v-container fluid class="application">
    <v-layout text-center wrap>
        <v-flex mb-4>
            <br />
            <h1>กรุณาเลือกรายการที่ต้องการทำ</h1>
        </v-flex>
    </v-layout>
    <v-row align="center">
        <v-col cols="12" sm="6">
            <v-card max-width="500" class="mx-auto">
                <v-row>
                    <v-col class="text-center" cols="12" sm="15">
                        <h3 style="margin-bottom: 20px;">เพิ่มข้อมูล</h3>
                        <v-btn large width="400" rounded color="primary" dark href="/cus" target="_self">สมัครสมาชิก</v-btn><br /><br />

                        <v-btn large width="400" rounded color="primary" dark href="/car" target="_self">เพิ่มรถเช่า</v-btn><br /><br />

                        <v-btn large width="400" rounded color="primary" dark href="/rentcar" target="_self">เช่ารถ</v-btn><br /><br />

                        <v-btn large width="400" rounded color="primary" dark href="/pay" target="_self">ชำระเงิน</v-btn><br /><br />

                        <v-btn large width="400" rounded color="primary" dark href="/returncar" target="_self">คืนรถ</v-btn>
                    </v-col>
                </v-row>
            </v-card>
        </v-col>

        <p></p>
        <v-col cols="12" sm="6">
            <v-card max-width="500" class="mx-auto">

                <v-row>
                    <v-col class="text-center" cols="12" sm="15">
                        <h3 style="margin-bottom: 20px;">ค้นหาและแสดงข้อมูล</h3>
                        <v-btn width="400" large rounded color="amber darken-1" dark href="/viewcustomer" target="_self">ค้นหาและแสดงข้อมูลสมาชิก</v-btn><br /><br />

                        <v-btn width="400" large rounded color="amber darken-1" dark href="/cardata" target="_self">ค้นหาและแสดงข้อมูลรถเช่า</v-btn><br /><br />

                        <v-btn width="400" large rounded color="amber darken-1" dark href="/viewdatarent" target="_self">ค้นหาและแสดงข้อมูลการเช่ารถ</v-btn><br /><br />

                        <v-btn width="400" large rounded color="amber darken-1" dark href="/viewpay" target="_self">ค้นหาและแสดงข้อมูลการชำระเงิน</v-btn><br /><br />

                        <v-btn width="400" large rounded color="amber darken-1" dark href="/viewredata" target="_self">ค้นหาและแสดงข้อมูลการคืนรถ</v-btn>
                    </v-col>
                </v-row>
            </v-card>
        </v-col>
    </v-row>

</v-container>
</template>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Kanit");

.application {
    font-family: "Kanit";
}
</style>

<script>

</script>
