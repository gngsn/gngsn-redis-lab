import {useEffect, useState} from 'react'
import './App.css'

const HOST = "http://localhost:8080/api/v1"

interface Tweet {
    id: number
    message: string
    like: number
}

const Feed = () => {
    const [tweet, setTweet] = useState<string>('')
    const [feed, setFeed] = useState<Tweet[]>([])

    const getTweet = () => {
        fetch(HOST + "/tweet")
            .then(res => res.json())
            .then((result) => setFeed(result),
                (error) => console.log(error))
    }

    const getRandom = (max: number = 1000): string => {
        return Math.floor(Math.random() * max).toString();
    }

    const sendTweet = (message: string) => {
        fetch(HOST + `/tweet`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId: getRandom(),
                message: message,
            })
        })
            .then(_ => {
                    setTweet('')
                    getTweet()
                },
                (error) => console.log(error)
            )
    }

    const clickLike = (id: number) => {
        fetch(HOST + `/tweet/like/${id}`, {
            method: "POST",
            headers: {
                "userId": getRandom()
            }
        })
            .then(_ => getTweet(), (error) => console.log(error))
    }

    const deleteTweet = (id: number) => {
        fetch(HOST + `/tweet/${id}`, {
            method: "DELETE",
        })
            .then(_ => getTweet(),
                (error) => console.log(error))
    }

    useEffect(() => {
        getTweet()
    }, [])

    return (
        <>
            <div>
                <div style={{display: 'flex', gap: '20px', alignItems: 'center', margin: '50px 0'}}>
                    <textarea value={tweet} onChange={e => setTweet(e.target.value)}/>
                    <button onClick={_ => sendTweet(tweet)}>Tweet</button>
                </div>
                {
                    feed.map(tweet =>
                        <div key={tweet.id}
                             style={{display: 'flex', gap: '5px', alignItems: 'center', margin: '10px 0'}}>
                            <h3 style={{marginRight: '30px'}}>✔️ &nbsp;{tweet.message}</h3>
                            <button onClick={_ => {
                                clickLike(tweet.id)
                            }}>🩵 {tweet.like}</button>
                            <button onClick={_ => {
                                deleteTweet(tweet.id)
                            }}>X
                            </button>
                        </div>
                    )
                }
            </div>
        </>
    )
}

export default Feed
