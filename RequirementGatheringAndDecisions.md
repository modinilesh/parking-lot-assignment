## Key features  
 - Multiple floors
 - Multi vehicle support
 - Available spot detection
 - Price calculations
 - Entry gate exit gate 


## Questions which I would have asked 
- Do we have single parking lot or can have multiple ? -> (as of now proceed with single parking lot)
- On a single floor, will there be single entry/exit or multiple -> (single)
- Which vehicles are allowed ? -> (Bike, Car, Bus and extensible)
- How price needs to be calculated ? -> (Take a base price per vehicle and calculate based on how much time it stayed)
- Will pricing strategy be same or can be changed ? -> (can be changed, like on festive or concert we can have fixed pricing per vehicle)
- Which payment modes are accepted ? -> (multi support, card, upi, cash, extensible)
- What if two vehicles come at a time to entry ? -> (Handle properly, only one should be allocated and another should be denied)


## Key approach I can think of / Important decisions
- Entities : Floor, Entry/Exit gate, Vehicle (Bike, Car, Bus), Ticket, Spot
- Pricing strategy : strategy design pattern with TIME_BASED and EVENT_BASED strategies
- Payment Methods : strategy design to decide which payment method to use : UPI, CASH, CARD
- At a time spot can be allocated to single vehicle only


## Basic workflow 
- Vehicle comes to entry 
- -> based on type find the available spot (which spot would you return first if multiples are available ?)
- -> Fill the spot, and store the Time
- -> On exit -> calculate time stayed, calculate price, ask payment -> generate ticket and free up spot
