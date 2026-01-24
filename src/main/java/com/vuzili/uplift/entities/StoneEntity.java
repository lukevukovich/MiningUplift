package com.vuzili.uplift.entities;
import com.vuzili.uplift.init.ItemInit;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StoneEntity extends ZombieEntity {

	private int exampleTimer;

	public StoneEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}

	@Override
	public void livingTick() {
		if (this.world.isRemote) {
			this.exampleTimer = Math.max(0, this.exampleTimer - 1);
		}
		super.livingTick();
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}
	
	@Override
	public boolean canEntityBeSeen(Entity entity) {
	    // Check if the entity is a player
	    if (entity instanceof PlayerEntity) {
	        PlayerEntity player = (PlayerEntity) entity;
	        // Check if the player has the invisibility effect
	        if (player.isPotionActive(Effects.INVISIBILITY)) {
	            // Return false because the player is invisible
	            return false;
	        }
	    }
	    // For non-player entities or players without invisibility, use the default behavior
	    return super.canEntityBeSeen(entity);
	}


	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.exampleTimer = 40;
		} else {
			super.handleStatusUpdate(id);
		}

	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_) {
		if (this.exampleTimer <= 0) {
			return 0.0F;
		} else if (this.exampleTimer >= 4 && this.exampleTimer <= 36) {
			return 1.0F;
		} else {
			return this.exampleTimer < 4 ? ((float) this.exampleTimer - p_70894_1_) / 4.0F
					: -((float) (this.exampleTimer - 40) - p_70894_1_) / 4.0F;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_) {
		if (this.exampleTimer > 4 && this.exampleTimer <= 36) {
			float f = ((float) (this.exampleTimer - 4) - p_70890_1_) / 32.0F;
			return ((float) Math.PI / 5F) + 0.21991149F * MathHelper.sin(f * 28.7F);
		} else {
			return this.exampleTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * ((float) Math.PI / 180F);
		}
	}
	
	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHit) {
	    super.dropSpecialItems(source, looting, recentlyHit);
	    if (this.world.isRemote) return; // We don't want to drop items on the client side.

	    // Check if the source of damage is a player
	    if (source.getTrueSource() instanceof PlayerEntity) {
		        // Example of dropping a vanilla item if killed by a player.
		    	this.entityDropItem(new ItemStack(ItemInit.ruby.asItem(), rand.nextInt(2)));
		    	this.entityDropItem(new ItemStack(ItemInit.sapphire.asItem(), rand.nextInt(2)));
		    	this.entityDropItem(new ItemStack(ItemInit.tourmaline.asItem(), rand.nextInt(2)));
		    	this.entityDropItem(new ItemStack(ItemInit.stone_meal, rand.nextInt(2)));
	    }
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
	    // Check if the damage is from falling
	    if (source == DamageSource.FALL) {
	        // Prevent fall damage by returning false
	        return false;
	    }
	    // Handle other types of damage normally
	    return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
	    // Get difficulty-based damage multiplier
	    float damageMultiplier;
	    switch (this.world.getDifficulty()) {
	        case EASY:
	            damageMultiplier = 0.5F;
	            break;
	        case NORMAL:
	            damageMultiplier = 1.0F;
	            break;
	        case HARD:
	            damageMultiplier = 1.5F;
	            break;
	        default:
	            damageMultiplier = 1.0F;
	            break;
	    }
	    
	    // Calculate scaled damage
	    float baseDamage = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
	    float scaledDamage = baseDamage * damageMultiplier;
	    
	    // Apply the damage to the target
	    boolean attacked = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), scaledDamage);
	    
	    if (attacked) {
	        this.applyEnchantments(this, entityIn);
	    }
	    
	    return attacked;
	}
	
	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
	    // Check if the world's difficulty is not Peaceful
	    if (worldIn.getDifficulty() == Difficulty.PEACEFUL) {
	        return false; // Do not allow spawning in Peaceful mode
	    }
	    
	    // Allow spawning for other difficulties
	    return super.canSpawn(worldIn, spawnReasonIn);
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return SoundEvents.ENTITY_VILLAGER_HURT;
	}
	
	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.ENTITY_VILLAGER_DEATH;
	}

	protected SoundEvent getFallSound(int heightIn) 
	{
		return heightIn > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
	}
	
	protected SoundEvent getAmbientSound() {
	    return SoundEvents.ENTITY_VILLAGER_AMBIENT;
	}
	
}