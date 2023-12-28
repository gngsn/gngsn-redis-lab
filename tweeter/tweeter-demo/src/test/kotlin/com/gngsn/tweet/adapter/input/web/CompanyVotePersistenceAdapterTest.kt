package com.gngsn.tweet.adapter.input.web

import com.gngsn.tweet.adapter.output.persistence.TweetPersistenceAdapter
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CompanyVotePersistenceAdapterTest {


    fun aggregate() {
        val companyIds = (0 until 10L).random()
        // 각 Company ID 당 100개의 투표 결과 생성
        val tweetPersistenceAdapter = mockk<TweetPersistenceAdapter>()

//        every {
//            tweetPersistenceAdapter.save(
//                (0 until 300L).random(), (0 until 10L).random()
//            )
//        } returns Outcome.OK
//
//        companyIds.forEach {
//            Mockito.`when`(getUserVoteOutputPort.findAll(it)).thenReturn(
//                (0 until 100L).map { i -> UserVoteEntitySpy.INSTANCE.create(i) }
//            )
//        }

        companyVotePersistenceAdapter.aggregate(companyIds)

        verify { }
    }
}

//enum class CommandSpy {
//    INSTANCE;
//
//    fun create() =
//        this.create(
//            "1",
//            Fairy.create().baseProducer().randomBetween(1L, 100L),
//            Fairy.create().baseProducer().randomBetween(0, 3),
//        )
//
//    fun create(userId: String, companyId: Long, option: Int) =
//        VoteUserCompanyInputPort.Command(
//            userId = userId,
//            companyId = companyId,
//            option = option,
//        )
//}
//
//enum class UserVoteEntitySpy {
//    INSTANCE;
//
//    fun create(companyId: Long) =
//        UserVoteEntity(
//            id = Fairy.create().baseProducer().randomInt(10000).toLong(),
//            userId = userId,
//            companyId = companyId,
//            score = Fairy.create().baseProducer().randomBetween(0, 4),
//            meta = Fairy.create().textProducer().text(),
//            updatedAt = LocalDateTime.now(),
//            deletedAt = null
//        )
//}