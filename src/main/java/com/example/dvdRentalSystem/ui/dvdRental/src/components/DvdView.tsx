import { useState } from 'react'
import './DvdView.css'

interface Film {
  id: number
  title: string
  description: string
  releaseYear: number
  rating: string
  specialFeatures: string
}

const DvdView = () => {
  const [title, setTitle] = useState('North')
  const [films, setFilms] = useState<Film[]>([])

  const searchFilms = async () => {
    try {
      const response = await fetch(`http://localhost:8080/dvd/search/films?title=${title}`)
      const data: Film[] = await response.json()
      setFilms(data)
    } catch (err) {
      console.error('Error fetching films:', err)
    }
  }

  return (
    <div>
      <h1>🎬 DVD Rental System</h1>

      <div className="search-bar">
        <input
          type="text"
          value={title}
          onChange={e => setTitle(e.target.value)}
          placeholder="Search for a film..."
        />
        <button onClick={searchFilms}>Search</button>
      </div>

      {films.length === 0 ? (
        <p className="no-films">No films found</p>
      ) : (
        <div className="films-grid">
          {films.map(film => (
            <div className="film-card" key={film.id}>
              <h2>{film.title}</h2>
              <p className="film-info">{film.releaseYear} - {film.rating}</p>
              <p className="film-desc">{film.description}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  )
}

export default DvdView
