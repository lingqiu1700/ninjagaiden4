package ninjagaiden4.modcards.Takeminakata.Blood;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.ModHelper;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;
import static ninjagaiden4.modcore.Ninja4.CardFields.dismemberRate;


public class Vaulting_Stance_Impact extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:Vaulting_Stance_Impact";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Vaulting_Stance_Impact.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Vaulting_Stance_Impact() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 28;
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        dismemberRate.set(this,0.35F);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(12);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new core(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), this));
        ModHelper.PAP(new DexterityPower(p,2));

        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(
                m,    // 传 m，代表“排除玩家选中的这个怪”，确保一定打在另一个怪身上
                true, // 只找活着的
                AbstractDungeon.cardRandomRng
        );

        if (randomMonster != null) {
            addToBot(new core(randomMonster, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), this));
        }
    }
}
