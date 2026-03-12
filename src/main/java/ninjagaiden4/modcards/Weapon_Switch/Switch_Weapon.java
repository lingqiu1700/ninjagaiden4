package ninjagaiden4.modcards.Weapon_Switch;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.relics.Takeminakata;
import ninjagaiden4.relics.Yatousen;

import java.util.ArrayList;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;

public class Switch_Weapon extends CustomCard {
    public static final String ID = ModHelper.makeID("Switch_Weapon");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/skill/Switch_Weapon.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Switch_Weapon() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use (AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> tmp = new ArrayList<>();

        if (!p.hasRelic(Takeminakata.ID)) {
            tmp.add(new Option_Takeminakata());
        }
        if (!p.hasRelic(Yatousen.ID)) {
            tmp.add(new Option_Yatousen());
        }

        if (!tmp.isEmpty()) {
            addToBot(new ChooseOneAction(tmp));
        }
    }
}
