package ninjagaiden4.modcards.Takeminakata.Blood;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.Ninja4;
import ninjagaiden4.modcore.WeaponTags;


import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;


public class Serpent_Cleave extends CustomCard implements WeaponCard {
    public static final String ID = "ninjagaiden4:Serpent_Cleave";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Serpent_Cleave.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Serpent_Cleave() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 16;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        Ninja4.CardFields.dismemberRate.set(this,0.25F);
        this.tags.add(WeaponTags.WEAPON_TAKEMINAKATA);
        this.tags.add(WeaponTags.BLOOD_F);
        this.tags.add(WeaponTags.Y);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
            this.upgradeMagicNumber(1);

            this.target = CardTarget.ALL_ENEMY;
            this.isMultiDamage = true;

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        ModHelper.PAP(new StrengthPower(p,3));
        ModHelper.PAP(new LoseStrengthPower(p,3));

        if (!this.upgraded) {
            ModHelper.Loop(1,this.magicNumber,i -> addToBot(new core(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), this)));
        }else {
            ModHelper.Loop(1,this.magicNumber,i -> AttackHelper.AoeAction(p,this,(monster,info) -> new core(monster,info,this)));
        }
    }
}

