package ninjagaiden4.modcards.Yatousen;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.ModHelper;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;
import static ninjagaiden4.modcore.Ninja4.CardFields.dismemberRate;


public class Hollow_Thrust extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:Hollow_Thrust";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Hollow_Thrust.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Hollow_Thrust() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 12;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        dismemberRate.set(this,0.2F);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new core(m,new DamageInfo(p, this.damage,DamageInfo.DamageType.NORMAL),this));
    }

}
