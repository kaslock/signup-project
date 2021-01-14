<template>
    <div id="header" v-if="isHeader">
        <h1>
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                SS_log
            </router-link>
        </h1>
        <div class="right">
            <div class="search-input">
                <i class="fas fa-search"></i>
                <input v-model="keyword" type="text"/>
            </div> 
            
                <a @click="logout" class="login-btn" style="color:black" v-if="isLoggedIn">
                    로그아웃
                </a>

                <router-link v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="login-btn" v-else>
                    로그인
                </router-link>
            
            
        </div>  
        
    </div>
</template>   

<script> 
    import constants from '../../lib/constants'

    export default {
        name: 'Header',
        components: { 

        },
        props: ['isHeader'],
        computed:{
            isLoggedIn() {
                return this.$store.state.loginData;
            }
        },
        watch: {
        },
        created() {
        },
        methods : {
            logout(){
                sessionStorage.removeItem("id");
                this.$store.commit("SET_LOGIN_DATA", "");
                this.$router.push("/user/login");
            }
        },
        mounted(){

        },
        data: function() {
           return {
               constants,
               keyword : "",
           }
        },

    }
</script>
