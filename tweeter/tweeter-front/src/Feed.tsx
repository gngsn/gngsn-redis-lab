import { useEffect, useState } from 'react'
import './App.css'
const HOST = "http://localhost:8080/api/v1"

interface Tweet {
  message: string
  like: number
}

const Feed = () => {
  const [tweets, setTweets] = useState<Tweet[]>([])

  const getTweet = () => {
    fetch(HOST + "/tweet")
      .then(res => res.json())
      .then((result) => {
          setTweets(result)
        },
        
        (error) => {
          console.log(error)
        }
      )
  }

  useEffect(() => {
    getTweet()
  }, [tweets])

  return (
    <>
      <div>
        {
          tweets.map(tweet => <>
            <p>✔️ {tweet.message} 🩵 {tweet.like}</p>
          </>)
        }
      </div>
    </>
  )
}

export default Feed
