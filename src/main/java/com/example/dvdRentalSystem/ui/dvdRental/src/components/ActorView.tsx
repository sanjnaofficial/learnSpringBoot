import { useState } from 'react'
import './ActorView.css'

interface Actor {
  id: number
  firstName: string
  lastName: string
}

const ActorView = () => {
  const [name, setName] = useState('')
  const [actors, setActors] = useState<Actor[]>([])

  const searchActors = async () => {
    try {
      const response = await fetch(`http://localhost:8080/dvd/search/actors?name=${name}`)
      const data: Actor[] = await response.json()
      setActors(data)
    } catch (err) {
      console.error('Error fetching actors:', err)
    }
  }

  return (
    <div>
      <h1>🎭 Actors</h1>

      <div className="search-bar">
        <input
          type="text"
          value={name}
          onChange={e => setName(e.target.value)}
          placeholder="Search for an actor..."
        />
        <button onClick={searchActors}>Search</button>
      </div>

      {actors.length === 0 ? (
        <p className="no-actors">No actors found</p>
      ) : (
        <ul className="actor-list">
          {actors.map(actor => (
            <li key={actor.id}>
              {actor.firstName} {actor.lastName}
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default ActorView
