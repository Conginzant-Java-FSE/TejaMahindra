const config = {
  theme: "dark",
  version: 2
} as const

type Theme = typeof config.theme

//let theme: Theme = "dark"   //  works beacuse dark and dark matches for theme
let theme: Theme = "light"  // Type error, becuase we declared dark but here we gave light, no match , so error will raise
