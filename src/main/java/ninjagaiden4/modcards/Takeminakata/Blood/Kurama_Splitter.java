package ninjagaiden4.modcards.Takeminakata.Blood;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.modcore.Ninja4;
import ninjagaiden4.powers.Flight_Down;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;


public class Kurama_Splitter extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:Kurama_Splitter";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Kurama_Splitter.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Kurama_Splitter() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 8;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        Ninja4.CardFields.dismemberRate.set(this,0.25F);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
            this.upgradeMagicNumber(2);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new core(m,new DamageInfo(p,20,DamageInfo.DamageType.NORMAL),this));

        for (int i = 1; i < this.magicNumber; i++) {
            addToBot(new core(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), this));
        }
        ModHelper.PAP(new FlightPower(p,1));
        ModHelper.PAP(new Flight_Down(p,1));
    }
}
