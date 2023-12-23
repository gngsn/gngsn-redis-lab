# POC Code

레디스를 사용한 예감 카운터 구현 POC 입니다. [@dun.jeong](https://kakaobank.agit.io/users/300243316)

## *✔️ Pseudo Code*

```
IF not exist user_vote(user_id = U, company_id = C):
    INSERT user_vote(user_id = U, company_id = C, option = O, score +1)
    Redis.DB1.hincrby(company_id = C, option = O, +1)
    Redis.DB2.zincrby(company_id = C, +1)
ELSE:
    UPDATE user_vote(user_id = U, company_id = C, option = O, 
                     prev_score -1, new_score +1)
    Redis.DB1.hincrby(company_id = C, option = NEW_O, +1)
    Redis.DB1.hincrby(company_id = C, option = PREV_O, -1)
```

_U = user\_id,_
_C = company\_id,_
_O = 유저가 선택한 옵션, PREV\_O = 기존에 선택한 옵션, NEW\_O = 새로 선택한 옵션_

## *✔️* TL;DR

```
VOTE = 1
UNVOTE = -1

def votes_first(company_id, voted_option):
    r.hincrby(company_vote_key(company_id), voted_option, VOTE)
    r_total.zincrby(ALL_VOTED_KEY, company_id, VOTE)

def vote_again(company_id, prev_option, next_option):
    r.hincrby(company_vote_key(company_id), prev_option, UNVOTE)
    r.hincrby(company_vote_key(company_id), next_option, VOTE)

company_id = 56

# 유저가 2번 투표
votes_first(company_id, 2)

# 사용자가 '2번 → 1번' 투표 수정
vote_again(company_id, 2, 1)
```