package com.jnu.sharedtodolist

import android.content.Context


// 싱글턴 적용
// 오브젝트로해서 사용하면 어디서든 사용 가능하다.
object SharedManager {

    // 데이터 저장
    fun storeUserInfo(id: String, userName: String, userAge: Int){

        // 1. 쉐어드 가져오기
        // 쉐어드 가져올때 context 가 필요하다
        //App.instance 여기까지가 context 나머지가 쉐어드 가져오기 그후 이름을만들어 그안에 키와 값을 넣는것이다.
                                                                            // 거희다 Context.MODE_PRIVATE 로 사용한다.
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // 2. 쉐어드에 에디터 가져오기
        val editor = shared.edit()

        // 3. 에디터에 데이터를 넣고
        //키, 값
        editor.putString("user_id", id)
        editor.putString("user_name", userName)
        editor.putInt("user_age", userAge)

        // 4. 에디터 변경사항을 적용
        editor.apply()
    }

    // 데이터 가져오기
    fun getUserName() : String {
        // 1. 쉐어드 가져오기
        // 쉐어드 가져올때 context 가 필요하다
        //App.instance 여기까지가 context 나머지가 쉐어드 가져오기 그후 이름을만들어 그안에 키와 값을 넣는것이다.
        // 거희다 Context.MODE_PRIVATE 로 사용한다.
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // !! 2개는 데이터 꼭있따.!
        val storedUserName = shared.getString("user_name", "")!!

        return storedUserName
    }

    fun storeUser(user: User){
        // 1. 쉐어드 가져오기
        // 쉐어드 가져올때 context 가 필요하다
        //App.instance 여기까지가 context 나머지가 쉐어드 가져오기 그후 이름을만들어 그안에 키와 값을 넣는것이다.
        // 거희다 Context.MODE_PRIVATE 로 사용한다.
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)

        // 2. 쉐어드에 에디터 가져오기
        val editor = shared.edit()

        // 3. 에디터에 데이터를 넣고
        //키, 값
        editor.putString("user_id", user.id)
        editor.putString("user_name", user.name)
        editor.putInt("user_age", user.age!!)
                                    //데이터가 없을수도있다고해서 오류떠서 꼭있따고 !! 두개써줌
        // 4. 에디터 변경사항을 적용
        editor.apply()
    }

    // 사용자 모델 가져오기
    // 데이터를 하나하나 가져오기 귀찮아 한번에 가져오기위해  User 모델(Class)을 만들었다.
    fun getUser(): User {
        // 1. 쉐어드 가져오기
        // 쉐어드 가져올때 context 가 필요하다
        //App.instance 여기까지가 context 나머지가 쉐어드 가져오기 그후 이름을만들어 그안에 키와 값을 넣는것이다.
        // 거희다 Context.MODE_PRIVATE 로 사용한다.
        val shared = App.instance.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val storedUserName = shared.getString("user_name", "")!!
        val storedUserId = shared.getString("user_id", "")!!
        val storedUserAge = shared.getInt("user_age", 0)!!

        val storedUser = User(
            storedUserId,
            storedUserName,
            storedUserAge
        )

        return storedUser


    }

}

