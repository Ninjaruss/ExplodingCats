# Original
## UI Mock-up
<img width="852" alt="Screen Shot 2019-11-07 at 6 28 02 PM" src="https://user-images.githubusercontent.com/23036145/68445448-d3494680-018e-11ea-9423-e24a06c7ac79.png">

## Description:
- There are 2 players, one pile of card to draw from, and one pile of card for discard.
- There are 2 types of cards, action cards, and explode/defuse cards.
- In each player’s turn, player play as many action cards as they want, and end the turn with drawing a card.
- The last person alive wins the game.

## Explode/Defuse;
- When player draw an explode card, they explode and lose unless they can play a defuse card. 
- If player played defuse card, the player can insert the explode card back to the draw deck secretly. 

## Action Cards:
- See the future: when player plays this card, the player can see the top 3 cards in the draw pile.
- Skip: when player plays this card, the player ends their turn without drawing.
- Shuffle: when player plays this card, the player can shuffle the draw pile.
- Two of a kind: If a player play 2 of a kind cards, the player can blindly take a card from the opposite player.
- Three of a kind: If a player play 3 of a kind cards, the player can name the card to take from the opposite player.
- Nope: this card can be played at anytime during the game to stop any actions except explode and defuse.

## Breakdown of Features and Team Member Tasks:
*- Game loop  :  Yan &  Russell
*- Card Manipulation  :  Russell
*- UI  :  Tyler  
- Lobby:  Siam
- Matchmaking  :  Tyler &  Yan
- Leaderboard  :  Siam 

## How to run locally
1. In vscode, go to the terminal and type "cd react".
2. then, type "npm run-script build"
3. if it works, then type "npm run-script start". the development server will start and a localhost://3000 will open with the webapp.
4. then start the back end by going to intellij and open spark-->src-->SparkDemo.java. click the play button.
