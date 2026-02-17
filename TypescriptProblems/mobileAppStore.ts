interface MobileApp {
  name: string
  developer: string
  downloads: number
  rating?: number
  isPremium: boolean
}

const app1: MobileApp = {
  name: "ChatFast",
  developer: "Tech Corp",
  downloads: 1000000,
  rating: 4.8,
  isPremium: false
}

const app2: MobileApp = {
  name: "PhotoEdit Pro",
  developer: "Creative Labs",
  downloads: 500000,
  isPremium: true
}

const app3: MobileApp = {
  name: "GameZone",
  developer: "Fun Studios",
  downloads: 2000000,
  rating: 4.3,
  isPremium: false
}

function isHighlyRated(app: MobileApp): boolean {
  return app.rating !== undefined && app.rating >= 4.5
}


console.log(app1.name, "Highly Rated:", isHighlyRated(app1))
console.log(app2.name, "Highly Rated:", isHighlyRated(app2))
console.log(app3.name, "Highly Rated:", isHighlyRated(app3))
