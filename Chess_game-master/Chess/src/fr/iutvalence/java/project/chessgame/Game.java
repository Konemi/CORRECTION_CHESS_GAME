package fr.iutvalence.java.project.chessgame;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import fr.iutvalence.java.project.gui.GameGUI;


public class Game
{
      /** Enumération des états possibles d'une partie. */
      public static enum State
      {

            IN_PROGRESS,

            BLACK_WINS,

            WHITE_WINS,


            STALEMATE,

            DRAWN_BY_TRIPLE_REPETITION,


            DRAWN_BY_50_MOVE_RULE;
      }

      /** Temps (en ms) alloué à un joueur, pour une partie. */
      private static final long GAME_DURATION = 15 * 60 * 1000;

      /** Mouvement courant (>= 0). */
      public static int currentMove;

      /** Liste des mouvements éxécutés. */
      public static List<Movement> everyLastMoves = new ArrayList<Movement>();


      public static Echiquier board;

      public static GameGUI userInterface;

      public static ColorEnum curPlayer;

      public static ColorEnum lastPlayer;

      private final Player blackPlayer;
      
      private final Player whitePlayer;

      private boolean isWhiteTurn;

      long blackTimer;
      
      long whiteTimer;

      private Timer timer;      
      
      /** Dernier temps de jeu */
      private long lastTimerTick;

      public Game()
      {
            Game.userInterface = new GameGUI(this);
            this.blackPlayer = new Player(ColorEnum.BLACK);
            this.whitePlayer = new Player(ColorEnum.WHITE);
            this.itIsWhiteTurn();
            Game.curPlayer = ColorEnum.WHITE;
            Game.lastPlayer = null;
            Game.currentMove = -1;
            Game.board = new Echiquier();
      }

      /**
       * @return the _moves
       */
      public List<Movement> get_moves()
      {
            return Game.everyLastMoves;
      }

      /**
       * @return the board
       */
      public Echiquier getBoard()
      {
            return board;
      }

      /**
       * @return the curPlayer
       */
      public ColorEnum getCurPlayer()
      {
            return Game.curPlayer;
      }

      /**
       * @return the currentMove
       */
      public int getCurrentMove()
      {
            return Game.currentMove;
      }

      /**
       * @return the userInterface
       */
      public GameGUI getUserInterface()
      {
            return userInterface;
      }

      /**
       * Determine if it is the end of the game
       *
       * @return false if the game isn't over
       */
      public boolean isEndOfGame(ColorEnum color)
      {
            if (color == ColorEnum.WHITE)
            {
                  King whiteKing = Game.board.getWhiteKing();
                  if (whiteKing.isCheck(whiteKing.getKingPosition()) && whiteKing.possibleMovements(whiteKing.getKingPosition()).isEmpty())
                  {
                        return true;
                  }
            }
            else
            {
                  King blackKing = Game.board.getBlackKing();
                  if (blackKing.isCheck(blackKing.getKingPosition()) && blackKing.possibleMovements(blackKing.getKingPosition()).isEmpty())
                  {
                        return true;
                  }
            }
            return false;
      }


      public boolean isWhiteTurn()
      {
            return isWhiteTurn;
      }

      public void isBlackTurn()
      {

            this.getUserInterface();
            GameGUI.joueur1.setBackground(Color.LIGHT_GRAY);
            this.getUserInterface();
            GameGUI.joueur2.setBackground(Color.WHITE);
            this.isWhiteTurn = false;

      }

      public void itIsWhiteTurn()
      {

            this.getUserInterface();
            GameGUI.joueur1.setBackground(Color.WHITE);
            this.getUserInterface();
            GameGUI.joueur2.setBackground(Color.LIGHT_GRAY);
            this.isWhiteTurn = true;
      }

      
      public void setCurPlayer(ColorEnum curPlayer)
      {
            Game.curPlayer = curPlayer;
      }

      public void setLastPlayer(ColorEnum color)
      {
            Game.lastPlayer = color;

      }


      public void setWhiteTurn(boolean isWhiteTurn)
      {
            this.isWhiteTurn = isWhiteTurn;
      }
}
