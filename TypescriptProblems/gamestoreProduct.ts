type Platform = "PC" | "PlayStation" | "Xbox" | "Mobile"

interface Game {
  title: string
  price: number
  platform: Platform
  inStock: boolean
}

const games: Game[] = [
  { title: "Cyber Battle", price: 59.99, platform: "PC", inStock: true },
  { title: "Speed Racer", price: 49.99, platform: "PlayStation", inStock: true },
  { title: "Galaxy War", price: 39.99, platform: "Xbox", inStock: false },
  { title: "Pocket Adventure", price: 9.99, platform: "Mobile", inStock: true },
  { title: "Mystic Quest", price: 29.99, platform: "PC", inStock: true }
]

function filterByPlatform(games: Game[], platform: Platform): Game[] {
  return games.filter(game => 
    game.platform === platform && game.inStock
  )
}

console.log("PC Games:", filterByPlatform(games, "PC"))
console.log("Mobile Games:", filterByPlatform(games, "Mobile"))
