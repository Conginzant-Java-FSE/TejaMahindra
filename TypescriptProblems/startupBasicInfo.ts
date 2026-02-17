interface Startup {
  name: string
  foundedYear: number
  totalFunding: number
  isPublic: boolean
}

const startup1: Startup = {
  name: "InnovateX",
  foundedYear: 2010,
  totalFunding: 50000000,
  isPublic: false
}

const startup2: Startup = {
  name: "NextGen Solutions",
  foundedYear: 2018,
  totalFunding: 10000000,
  isPublic: true
}

function isEstablished(startup: Startup): boolean {
  return startup.foundedYear < 2015
}

console.log(startup1.name, "Established:", isEstablished(startup1))
console.log(startup2.name, "Established:", isEstablished(startup2))
