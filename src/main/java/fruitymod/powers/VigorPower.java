package fruitymod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import fruitymod.FruityMod;

public class VigorPower extends AbstractPower implements PostDrawSubscriber, PostBattleSubscriber {
	public static final String POWER_ID = "VigorPower";
	public static final String NAME = "Vigor";
	public static final String[] DESCRIPTIONS = new String[] {
			"Ethereal cards cost ",
			" less energy."
	};
	
	public VigorPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.isTurnBased = false;
		this.priority = 90;
		this.img = FruityMod.getVigorPowerTexture();
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}
	
	@Override
	public void onInitialApplication() {
		BaseMod.subscribeToPostDraw(this);
		BaseMod.subscribeToPostBattle(this);
		for (AbstractCard c: AbstractDungeon.player.hand.group) {
			updateCardCost(c, true);
		}
	}
	
	@Override
	public void receivePostDraw(AbstractCard c) {
		updateCardCost(c, false);
	}
	
	private void updateCardCost(AbstractCard c, boolean forceUpdate) {
		if (c.isEthereal && (AbstractDungeon.player.hasPower("VigorPower") || forceUpdate)) {
			if (c.isCostModifiedForTurn) {
				c.setCostForTurn(c.costForTurn - this.amount);
			} else {
				c.setCostForTurn(c.cost - this.amount);
			}
		}
	}
	
	@Override
	public void receivePostBattle(AbstractRoom arg0) {
		BaseMod.unsubscribeFromPostDraw(this);
		/*
		 *  calling unsubscribeFromPostBattle inside the callback
		 *  for receivePostBattle means that when we're calling it
		 *  there is currently an iterator going over the list
		 *  of subscribers and calling receivePostBattle on each of
		 *  them therefore if we immediately try to remove the this
		 *  callback from the post battle subscriber list it will
		 *  throw a concurrent modification exception in the iterator
		 *  
		 *  for now we just add a delay - yes this is an atrocious solution
		 *  PLEASE someone with a better idea replace it
		 */
		Thread delayed = new Thread(() -> {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				System.out.println("could not delay unsubscribe to avoid ConcurrentModificationException");
				e.printStackTrace();
			}
			BaseMod.unsubscribeFromPostBattle(this);
		});
		delayed.start();
	}
	
}
