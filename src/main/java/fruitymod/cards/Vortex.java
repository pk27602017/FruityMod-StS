package fruitymod.cards;

import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import fruitymod.FruityMod;
import fruitymod.patches.AbstractCardEnum;

public class Vortex
extends CustomCard {
    public static final String ID = "Vortex";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int POOL = 1;

    public Vortex() {
        super(ID, NAME, FruityMod.makePath(FruityMod.VORTEX), COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.PURPLE,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, POOL);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new LoseBlockAction(m, p, m.currentBlock));
    }
    
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        }
    	if(m != null && (m.hasPower("Weakened") || m.hasPower("Vulnerable"))) {
			return true;
		}
		this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
		return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Vortex();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
            this.rawDescription = (this.isEthereal ? "Ethereal." : "") + cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}


