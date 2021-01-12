<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원가입</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <input v-model="nickName"
                            id="nickname"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap">
                        <input v-model="email" 
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="password"
                            id="password" 
                            :type="passwordType"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="passwordConfirm" 
                            id="password-confirm"
                            :type="passwordConfirmType"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
                    </div>
                </div>

                <label>
                    <input v-model="isTerm" type="checkbox" id="term"/>
                    <span>약관에 동의합니다</span>
                </label>

                <span class="go-term">약관 보기</span>

                <button class="btn" v-on:click="signup()">
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

    export default {
        components: {
        },
        created(){
        },
        methods: {
             async signup() {
                if (this.valid()) {
                    try{
                        // const {data} = await axios.post("http://localhost:8080/account/signup");
                    } catch(error) {
                        console.log(error);
                    }
                }

            },
            valid(){
                const nickName = this.nickName;
                const email = this.email;
                const emailCheck = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
                const password = this.password;
                const passwordConfirm = this.passwordConfirm;
                const num = password.search(/[0-9]/g);
                const eng = password.search(/[a-z]/ig);
                const isTerm = this.isTerm;
                
                if (!nickName){
                    alert("닉네임을 입력하세요.");
                    return false;
                }
                else if (!email){
                    alert("이메일을 입력하세요.");
                    return false;
                }
                else if (!emailCheck.test(email)){
                    alert("이메일 형식을 확인해주세요.");
                    return false;
                }
                else if(password.length < 8 || password.length > 16){
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
                else if (!isTerm) {
                    alert("약관에 동의해주세요.");
                    return false;
                }
                else {
                    console.log("password check success"); 
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
                password: '',
                passwordConfirm: '',
                isTerm: false,
                passwordType:"password",
                passwordConfirmType:"password",
            }
        }

    }

</script>


