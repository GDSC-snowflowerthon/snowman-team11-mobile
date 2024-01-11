package com.snowflowerthon.snowman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.tab_home -> {
                    // 다른 프래그먼트 화면으로 이동하는 기능
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, homeFragment).commit()
                }
                R.id.tab_archive -> {
                    val archiveFragment = ArchiveFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, archiveFragment).commit()
                }
                R.id.tab_vote -> {
                    val voteFragment = VoteFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, voteFragment).commit()
                }
                R.id.tab_profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, profileFragment).commit()
                }
            }
            true
        }
            selectedItemId = R.id.tab_home
        }
    }
}