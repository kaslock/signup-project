<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원수정</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <div id="email">
                            {{email}}
                        </div>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="newPassword"
                            id="password" 
                            :type="passwordType"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="newPasswordConfirm" 
                            id="password-confirm"
                            :type="passwordConfirmType"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
                    </div>
                </div>
                <button class="btn" v-on:click="update()">
                    <span>
                        작성완료
                    </span>
                </button>
            </div>


        </div> 
        

    </div>
</template>

<script>
    import '../../assets/css/user.scss'
    import axios from "axios";

    export default {
        components: {
        },
        created(){
        },
        methods: {
             async update() {
                const email = this.$store.state.loginData.id;
                const password = this.password;
                
                if (this.valid()) {
                    try{
                        const {data} = await axios.put("http://localhost:8080/account/update", {
                            email, newPassword
                        });
                        
                        const check = data.data;
                        
                        console.log(check);
                        if (check == "success") {
                            alert("수정이 완료되었습니다.");
                            this.$router.push("/");
                        } else {
                            alert(check);
                        }

                    } catch(error) {
                        console.log(error);
                    }
                }

            },
            valid(){
                const password = this.newPassword;
                const passwordConfirm = this.newPasswordConfirm;
                const num = password.search(/[0-9]/g);
                const eng = password.search(/[a-z]/ig);
            
                if(password.length < 8 || password.length > 16){
                    alert("8자리 ~ 16자리 이내로 입력해주세요.");
                    return false;
                }
                else if(password.search(/\s/) != -1){
                    alert("비밀번호는 공백 없이 입력해주세요.");
                    return false;
                }
                else if(num < 0 || eng < 0){
                    alert("8~16자 영문과 숫자를 사용하세요.");
                    return false;
                }
                else if (password != passwordConfirm){
                    alert("비밀번호가 일치하지 않습니다.");
                    return false;
                }
                else {
                    // console.log("password check success"); 
                    return true;
                }
            }
        },
        watch: {
        },
        data: () => {
            return {
                email: '',
                nickName: '',
                newPassword: '',
                newPasswordConfirm: '',
                passwordType:"password",
                passwordConfirmType:"password",
            }
        }

    }

</script>


