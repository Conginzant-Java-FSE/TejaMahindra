function filterAvailableItems<T extends { available: boolean }>(
  items: T[]
): T[] {
  return items.filter(item => item.available)
}

const games = [
  { title: "FIFA 25", available: true },
  { title: "CyberRacer", available: false }
]

console.log("Available are :", filterAvailableItems(games))
